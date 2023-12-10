package soa;

import org.junit.jupiter.api.Test;
import soa.entities.Client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConstructeurClientTest {

    @Test
    void testConstructeurAvecParametres() {
        // Créez un objet Client en utilisant le constructeur avec paramètres
        Client client = new Client(1L, "C123", "John", "Doe", "123 Street", "123456789", "john.doe@example.com");

        // Vérifiez que l'objet a été créé correctement en vérifiant ses propriétés
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
        // Créez un objet Client en utilisant le constructeur par défaut
        Client client = new Client();

        // Vérifiez que l'objet a été créé correctement (les propriétés peuvent être nulles ou avoir des valeurs par défaut)
        assertNotNull(client);
        // Ajoutez d'autres assertions selon votre implémentation
    }
}
