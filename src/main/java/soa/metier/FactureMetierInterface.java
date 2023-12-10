package soa.metier;

import soa.entities.Devise;
import soa.entities.Facture;

import java.util.List;

public interface FactureMetierInterface {

   double getMontantTotalAvecTVA(Facture facture);

   void ajouterFacture(Facture facture);

   List<Facture> listeFactures();


   void deleteFacture(Long facture);

   void updateFacture(Facture facture);





   Facture getFactureById(Long factureId);

   double convertirMontant(Facture facture);

   double sommeMontantFactureParClient(Long clientId);
}
