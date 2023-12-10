package soa;

import org.junit.jupiter.api.Test;
import soa.entities.Client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConstructeurClientTest {

    @Test
    void testConstructeurAvecParametres() {
        // Cr�ez un objet Client en utilisant le constructeur avec param�tres
        Client client = new Client(1L, "C123", "John", "Doe", "123 Street", "123456789", "john.doe@example.com");

        // V�rifiez que l'objet a �t� cr�� correctement en v�rifiant ses propri�t�s
        assertNotNull(client);
        assertEquals(1L, client.getId());
        assertEquals("C123", client.getCode());
        assertEquals("John", client.getNom());
        assertEquals("Doe", client.getPrenom());
        assertEquals("123 Street", client.getAdresse());
        assertEquals("123456789", client.getTel());
        assertEquals("john.doe@example.com", client.getEmail());
    }

    @Test
    void testConstructeurParDefaut() {
        // Cr�ez un objet Client en utilisant le constructeur par d�faut
        Client client = new Client();

        // V�rifiez que l'objet a �t� cr�� correctement (les propri�t�s peuvent �tre nulles ou avoir des valeurs par d�faut)
        assertNotNull(client);
        // Ajoutez d'autres assertions selon votre impl�mentation
    }
}
