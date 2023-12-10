package soa;

import org.junit.jupiter.api.Test;
import soa.entities.Devise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConstructeurDeviseTest {

    @Test
    void testConstructeurAvecParametres() {
        // Cr�ez un objet Devise en utilisant le constructeur avec param�tres
        Devise devise = new Devise("USD", "$", 1.5F);

        // V�rifiez que l'objet a �t� cr�� correctement en v�rifiant ses propri�t�s
        assertNotNull(devise);
        assertEquals("USD", devise.getCode());
        assertEquals("$", devise.getSymbole());
        assertEquals(1.5F, devise.getTaux());
    }

    @Test
    void testConstructeurParDefaut() {
        // Cr�ez un objet Devise en utilisant le constructeur par d�faut
        Devise devise = new Devise();

        // V�rifiez que l'objet a �t� cr�� correctement (les propri�t�s peuvent �tre nulles ou avoir des valeurs par d�faut)
        assertNotNull(devise);
        // Ajoutez d'autres assertions selon votre impl�mentation
    }
}
