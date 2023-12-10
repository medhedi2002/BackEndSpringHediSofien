package soa;

import org.junit.jupiter.api.Test;
import soa.entities.Client;
import soa.entities.Devise;
import soa.entities.Facture;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConstructeurFactureTest {

    @Test
    void testConstructeurAvecParametres() {
        // Créez un objet Client et un objet Devise pour les paramètres du constructeur
        Client client = new Client(1L, "c1", "John", "Doe", "123 Main St", "123456789", "john.doe@example.com");
        Devise devise = new Devise("USD", "$", 1.5F);

        // Créez un objet Facture en utilisant le constructeur avec paramètres
        Facture facture = new Facture("123", 100.0, new Date(), client, devise);

        // Vérifiez que l'objet a été créé correctement en vérifiant ses propriétés
        assertNotNull(facture);
        assertEquals("123", facture.getNumero());
        assertEquals(100.0, facture.getMontant());
        assertNotNull(facture.getDateF());
        assertEquals(client, facture.getClient());
        assertEquals(devise, facture.getDevise());
    }

    @Test
    void testConstructeurParDefaut() {
        // Créez un objet Facture en utilisant le constructeur par défaut
        Facture facture = new Facture();

        // Vérifiez que l'objet a été créé correctement (les propriétés peuvent être nulles ou avoir des valeurs par défaut)
        assertNotNull(facture);
        // Ajoutez d'autres assertions selon votre implémentation
    }
}
