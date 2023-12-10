package soa.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.entities.Facture;
import soa.metier.FactureMetierInterface;
import soa.repository.FactureRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FactureMetierImpl implements FactureMetierInterface {

    private final FactureRepository factureRepository;

    @Autowired
    public FactureMetierImpl(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }


    @Override
    public double getMontantTotalAvecTVA(Facture facture) {
        return 0;
    }

    @Override
    public void ajouterFacture(Facture facture) {
        // Ajoutez votre logique personnalisée, si nécessaire, avant d'enregistrer la facture
        factureRepository.save(facture);
    }




    @Override
    public void updateFacture(Facture facture) {
        if (factureRepository.existsById(facture.getId())) {
            factureRepository.save(facture);
        } else {
            throw new IllegalArgumentException("Facture with ID " + facture.getId() + " does not exist");
        }
    }

    @Override
    public void deleteFacture(Long factureId) {
        if (factureRepository.existsById(factureId)) {
            factureRepository.deleteById(factureId);
        } else {
            throw new IllegalArgumentException("Facture with ID " + factureId + " does not exist for deletion");
        }
    }


    @Override
    public Facture getFactureById(Long factureId) {
        Optional<Facture> optionalFacture = factureRepository.findById(factureId);
        return optionalFacture.orElse(null);
    }
    @Override
    public List<Facture> listeFactures() {
        return factureRepository.findAll();
    }

    @Override
    public double convertirMontant(Facture facture) {
        // Vérifiez si la facture et la devise de la facture sont non nulles
        if (facture != null && facture.getDevise() != null) {
            // Récupérez le montant de la facture
            double montantFacture = facture.getMontant();

            // Récupérez le taux de change de la devise
            double tauxDeChange = facture.getDevise().getTaux();

            // Convertissez le montant en fonction du taux de change
            return montantFacture * tauxDeChange;
        } else {
            throw new IllegalArgumentException("Facture et/ou devise nulle(s).");
        }
    }
    @Override
    public double sommeMontantFactureParClient(Long clientId) {
        List<Facture> factures = factureRepository.findAllByClientId(clientId);

        double sommeMontant = factures.stream()
                .mapToDouble(Facture::getMontant)
                .sum();

        return sommeMontant;
    }
}