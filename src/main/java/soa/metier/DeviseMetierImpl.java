package soa.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.entities.Devise;
import soa.entities.Facture;
import soa.repository.DeviseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DeviseMetierImpl implements DeviseMetierInterface {

    private final DeviseRepository deviseRepository;

    @Autowired
    public DeviseMetierImpl(DeviseRepository deviseRepository) {
        this.deviseRepository = deviseRepository;
    }
    @Override
    public List<Devise> listeDevises() {
        return deviseRepository.findAll();
    }

    @Override
    public double convertirMontantFacture(Facture facture, Devise deviseCible) {
        double tauxDeChange = deviseCible.getTaux();
        return facture.getMontant() * tauxDeChange;
    }

    @Override
    public double appliquerTauxTVA(Facture facture) {
        double tauxTVA = facture.getDevise().getTaux();
        return facture.getMontant() * (1 + tauxTVA);
    }

    @Override
    public void ajouterDevise(Devise devise) {
        deviseRepository.save(devise);
    }

    @Override
    public void saveClient(Devise devise) {

    }

    @Override
    public void updateDevise(Devise devise) {
        if (deviseRepository.existsById(devise.getId())) {
            deviseRepository.save(devise);
        } else {
            throw new IllegalArgumentException("Devise with ID " + devise.getId() + " does not exist");
        }
    }

    @Override
    public void deleteDevise(Long deviseId) {
        if (deviseRepository.existsById(deviseId)) {
            deviseRepository.deleteById(deviseId);
        } else {
            throw new IllegalArgumentException("Devise with ID " + deviseId + " does not exist for deletion");
        }
    }


    @Override
    public Devise getDeviseById(String deviseId) {
        try {
            Long id = Long.parseLong(deviseId);
            Optional<Devise> optionalClient = deviseRepository.findById(id);
            return optionalClient.orElse(null);
        } catch (NumberFormatException e) {
            // Handle the case where clientId is not a valid Long
            return null;
        }
    }
    }





