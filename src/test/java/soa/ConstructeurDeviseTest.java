package soa;

import org.junit.jupiter.api.Test;
import soa.entities.Devise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConstructeurDeviseTest {

    @Test
    void testConstructeurAvecParametres() {
        // Créez un objet Devise en utilisant le constructeur avec paramètres
        Devise devise = new Devise("USD", "$", 1.5F);

        // Vérifiez que l'objet a été créé correctement en vérifiant ses propriétés
        assertNotNull(devise);
        assertEquals("USD", devise.getCode());
        assertEquals("$", devise.getSymbole());
        assertEquals(1.5F, devise.getTaux());
    }

    @Test
    void testConstructeurParDefaut() {
        // Créez un objet Devise en utilisant le constructeur par défaut
        Devise devise = new Devise();

        // Vérifiez que l'objet a été créé correctement (les propriétés peuvent être nulles ou avoir des valeurs par défaut)
        assertNotNull(devise);
        // Ajoutez d'autres assertions selon votre implémentation
    }
}
