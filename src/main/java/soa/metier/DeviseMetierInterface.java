package soa.metier;

import soa.entities.Devise;
import soa.entities.Facture;

import java.util.List;

public interface DeviseMetierInterface {

    List<Devise> listeDevises();

    double convertirMontantFacture(Facture facture, Devise deviseCible);


    double appliquerTauxTVA(Facture facture);

    public void ajouterDevise(Devise devise);

    void saveClient(Devise devise);

    void updateDevise(Devise devise);


    void deleteDevise(Long deviseId);



    Devise getDeviseById(String deviseId);
}
