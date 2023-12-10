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
        // Cr�ez un objet Client et un objet Devise pour les param�tres du constructeur
        Client client = new Client(1L, "c1", "John", "Doe", "123 Main St", "123456789", "john.doe@example.com");
        Devise devise = new Devise("USD", "$", 1.5F);

        // Cr�ez un objet Facture en utilisant le constructeur avec param�tres
        Facture facture = new Facture("123", 100.0, new Date(), client, devise);

        // V�rifiez que l'objet a �t� cr�� correctement en v�rifiant ses propri�t�s
        assertNotNull(facture);
        assertEquals("123", facture.getNumero());
        assertEquals(100.0, facture.getMontant());
        assertNotNull(facture.getDateF());
        assertEquals(client, facture.getClient());
        assertEquals(devise, facture.getDevise());
    }

    @Test
    void testConstructeurParDefaut() {
        // Cr�ez un objet Facture en utilisant le constructeur par d�faut
        Facture facture = new Facture();

        // V�rifiez que l'objet a �t� cr�� correctement (les propri�t�s peuvent �tre nulles ou avoir des valeurs par d�faut)
        assertNotNull(facture);
        // Ajoutez d'autres assertions selon votre impl�mentation
    }
}
