package soa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import soa.entities.Client;
import soa.entities.Devise;
import soa.entities.Facture;
import soa.metier.ClientMetierInterface;
import soa.metier.DeviseMetierInterface;
import soa.metier.FactureMetierInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringJpaApplication implements CommandLineRunner {

    // Déclaration d'un objet métier pour gérer les devises
    private final DeviseMetierInterface deviseMetier;
    private final ClientMetierInterface clientMetier;
    private final FactureMetierInterface factureMetier;

    public SpringJpaApplication(DeviseMetierInterface deviseMetier, ClientMetierInterface clientMetier, FactureMetierInterface factureMetier) {
        this.deviseMetier = deviseMetier;
        this.clientMetier = clientMetier;
        this.factureMetier = factureMetier;
    }

    public static void main(String[] args) {
        System.out.println("---------Injection de dépendances----------");
        // Commencer par réaliser les injections de dépendances pour les objets de type Repository
        // référencer le contexte
        ApplicationContext contexte = SpringApplication.run(SpringJpaApplication.class, args);
        // Récupérer une implémentation de l'interface "DeviseMetierInterface" par injection de dépendance
       ClientMetierInterface clientMetier = contexte.getBean(ClientMetierInterface.class);
        // Récupérer une implémentation de l'interface "ClientMetierInterface" par injection de dépendance
        FactureMetierInterface factureMetier = contexte.getBean(FactureMetierInterface.class);

        // Afficher toutes les devises
    }

    // Mettez cette déclaration à l'intérieur de la méthode main

    @Override
    public void run(String... args) throws Exception {
        // Objet pour formater les dates selon le pattern "yyyy-MM-dd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        Date date3 = null;
        Date date4 = null;
        Date dateFinPromotion = null;

        // Trois objets de type date
        try {
            date1 = sdf.parse("2021-04-15");
            date2 = sdf.parse("2022-02-20");
            date3 = sdf.parse("2023-01-10");
            date4 = sdf.parse("2023-05-15");
            dateFinPromotion = sdf.parse("2023-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("-2-Insérer les clients/devises/factures  ----------");
        // Ajouter les clients c1 et c2
        Client c1 = new Client( "c1", "sofien", "ayedi", "sfax", "29544554", "sof@gmail.com");
        clientMetier.ajouterClient(c1);
        Client c2 = new Client( "c2", "hedi", "sahs", "sfax", "255555155", "sahs@gmail.com");
        clientMetier.ajouterClient(c2);
        Client c3 = new Client("c3", "John", "Doe", "New York", "123456789", "john.doe@example.com");
        clientMetier.ajouterClient(c3);
        Client c4 = new Client( "c4", "Jane", "Smith", "Los Angeles", "987654321", "jane.smith@example.com");
        clientMetier.ajouterClient(c4);

        // Afficher la liste des clients après l'ajout
        afficherListeClients(clientMetier);

        // Ajouter les devises
        Devise devise1 = new Devise("EUR", "Euro", 1.0F);
        Devise devise2 = new Devise("USD", "Dollar américain", 1.18F);
        Devise devise3 = new Devise("GBP", "British Pound", 0.85F);
        Devise devise4 = new Devise("JPY", "Japanese Yen", 130.0F);

        deviseMetier.ajouterDevise(devise1);
        deviseMetier.ajouterDevise(devise2);
         deviseMetier.ajouterDevise(devise3);
        deviseMetier.ajouterDevise(devise4);
        // Afficher la liste des devises après l'ajout
        afficherListeDevises(deviseMetier);

        // Ajouter les factures
        Facture facture1 = new Facture("F001", 100.0, "11-11-2020", c1, devise1);
        Facture facture2 = new Facture("F002", 150.0, "12-11-2020", c2, devise2);
        factureMetier.ajouterFacture(facture1);
        factureMetier.ajouterFacture(facture2);
        Facture facture3 = new Facture("F003", 200.0, "25-10-2023", c3, devise3);
        Facture facture4 = new Facture("F004", 75.0, "09-07-2020", c4, devise4);
        Facture facture5 = new Facture("F005", 45.0, "18-01-2019", c4, devise1);
        factureMetier.ajouterFacture(facture5);

        factureMetier.ajouterFacture(facture3);
        factureMetier.ajouterFacture(facture4);
        afficherListeFactures(factureMetier);
        // ... (existing code)
        System.out.println("-3-Mise à jour d'une devise  ----------");
        Devise deviseToUpdate = deviseMetier.getDeviseById(String.valueOf(1L));
        if (deviseToUpdate != null) {
            deviseToUpdate.setSymbole("Euro (EUR) - Mise à jour");
            deviseMetier.updateDevise(deviseToUpdate);
            System.out.println("Devise mise à jour avec succès: " + deviseToUpdate);
        } else {
            System.out.println("Devise non trouvée pour la mise à jour.");
        }

        // Delete a devise
        System.out.println("-4-Suppression d'une devise  ----------");
        Long deviseIdToDelete = 3L;
        deviseMetier.deleteDevise(deviseIdToDelete);
        System.out.println("Devise supprimée avec succès: " + deviseIdToDelete);

        // Afficher la liste des devises après la suppression

        // Update a facture
        System.out.println("-5-Mise à jour d'une facture  ----------");
        Facture factureToUpdate = factureMetier.getFactureById(1L); // Assuming there's a method to get a facture by ID
        if (factureToUpdate != null) {
            factureToUpdate.setMontant(140.0); // Update the montant
            factureMetier.updateFacture(factureToUpdate);
            System.out.println("Facture mise à jour avec succès: " + factureToUpdate);
        } else {
            System.out.println("Facture non trouvée pour la mise à jour.");
        }
        System.out.println("-6-Suppression d'une facture ----------");
        Long factureIdToDelete = 1L; // Specify the ID of the facture to delete
        factureMetier.deleteFacture(factureIdToDelete);
        System.out.println("Facture supprimée avec succès: " + factureIdToDelete);

        // Afficher la liste des factures après la suppression
        afficherListeFactures(factureMetier);
        System.out.println("-7-Test convertirMontant  ----------");
        Facture factureToConvert = new Facture("F005", 170.0, "11-11-2020", c2, devise4);
        factureMetier.ajouterFacture(factureToConvert);
        double convertedAmount = factureMetier.convertirMontant(factureToConvert);
        System.out.println("Montant converti: " + convertedAmount);

        // Test sommeMontantFactureParClient
        System.out.println("-8-Test sommeMontantFactureParClient  ----------");
        double totalAmountForClient1 = factureMetier.sommeMontantFactureParClient(c2.getId());
        System.out.println("Somme des montants pour le client 1: " + totalAmountForClient1);

    }

    private void afficherListeClients(ClientMetierInterface clientMetier) {
        System.out.println("********************Début**********************");
        System.out.println("Afficher la liste des clients...");
        System.out.println("***********************************************");

        // Utiliser la méthode listeClients du métier des clients
        List<Client> listeClients = clientMetier.listeClients();
        for (Client client : listeClients) {
            System.out.println(client);
        }

        System.out.println("********************Fin************************");
    }

    private void afficherListeDevises(DeviseMetierInterface deviseMetier) {
        System.out.println("********************Début**********************");
        System.out.println("Afficher la liste des devises...");
        System.out.println("***********************************************");

        // Utiliser la méthode listeDevises du métier des devises
        List<Devise> listeDevises = deviseMetier.listeDevises();
        for (Devise devise : listeDevises) {
            System.out.println(devise);
        }
        Facture sampleFacture = factureMetier.getFactureById(1L); // Assuming there's a method to get a facture by ID
        Devise sampleDevise = deviseMetier.getDeviseById(String.valueOf(1L)); // Assuming there's a method to get a devise by ID



        System.out.println("********************Fin************************");
    }

    private void afficherListeFactures(FactureMetierInterface factureMetier) {
        System.out.println("********************Début**********************");
        System.out.println("Afficher la liste des factures...");
        System.out.println("***********************************************");

        // Utiliser la méthode listeFactures du métier des factures
        List<Facture> listeFactures = factureMetier.listeFactures();
        for (Facture facture : listeFactures) {
            System.out.println(facture);
        }

        System.out.println("********************Fin************************");
    }
}
