package soa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import soa.entities.Client;
import soa.entities.Devise;
import soa.entities.Facture;
import soa.metier.FactureMetierImpl;
import soa.repository.FactureRepository;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class FactureTest {

    @Mock
    private FactureRepository factureRepository;

    @InjectMocks
    private FactureMetierImpl factureMetier;

    @Test
    void testAjouterFacture() {
        // Cr�ez une facture de test
        Facture facture = new Facture("123", 100.0, "11-09-2019", new Client(), new Devise());

        // Configurez le comportement du repository pour retourner la facture enregistr�e
        Mockito.when(factureRepository.save(any(Facture.class))).thenReturn(facture);

        // Appelez la m�thode � tester
        factureMetier.ajouterFacture(facture);

        // V�rifiez que la m�thode save du repository a �t� appel�e une fois avec la facture en argument
        Mockito.verify(factureRepository, times(1)).save(facture);

        // Vous pouvez ajouter d'autres assertions en fonction de votre logique m�tier
    }

    @Test
    void testUpdateFacture() {
        // Cr�ez une facture de test
        Facture facture = new Facture("123", 100.0, "14-02-2021", new Client(), new Devise());
        facture.setId(1L);

        // Configurez le comportement du repository pour retourner vrai lors de la v�rification de l'existence de la facture
        Mockito.when(factureRepository.existsById(1L)).thenReturn(true);

        // Appelez la m�thode � tester
        factureMetier.updateFacture(facture);

        // V�rifiez que la m�thode save du repository a �t� appel�e une fois avec la facture en argument
        Mockito.verify(factureRepository, times(1)).save(facture);

        // Vous pouvez ajouter d'autres assertions en fonction de votre logique m�tier
    }

    @Test
    void testDeleteFacture() {
        // Configurez le comportement du repository pour retourner vrai lors de la v�rification de l'existence de la facture
        Mockito.when(factureRepository.existsById(1L)).thenReturn(true);

        // Appelez la m�thode � tester
        factureMetier.deleteFacture(1L);

        // V�rifiez que la m�thode deleteById du repository a �t� appel�e une fois avec l'ID en argument
        Mockito.verify(factureRepository, times(1)).deleteById(1L);

        // Vous pouvez ajouter d'autres assertions en fonction de votre logique m�tier
    }

    @Test
    void testGetFactureById() {
        // Cr�ez une facture de test
        Facture facture = new Facture("123", 100.0, "14-02-2021", new Client(), new Devise());
        facture.setId(1L);

        // Configurez le comportement du repository pour retourner la facture en option
        Mockito.when(factureRepository.findById(1L)).thenReturn(Optional.of(facture));

        // Appelez la m�thode � tester
        Facture retrievedFacture = factureMetier.getFactureById(Long.valueOf(String.valueOf(1L)));

        // Ajoutez des assertions bas�es sur le comportement attendu
        assertNotNull(retrievedFacture);
        assertEquals(1L, retrievedFacture.getId());

        // Vous pouvez ajouter d'autres assertions en fonction de votre logique m�tier
    }

    @Test
    void testListeFactures() {
        // Cr�ez une liste de factures de test
        List<Facture> factures = Arrays.asList(
                new Facture("123", 100.0,"14-02-2021", new Client(), new Devise()),
                new Facture("456", 200.0,"14-02-2021", new Client(), new Devise())
        );

        // Configurez le comportement du repository pour retourner la liste de factures
        Mockito.when(factureRepository.findAll()).thenReturn(factures);

        // Appelez la m�thode � tester
        List<Facture> retrievedFactures = factureMetier.listeFactures();

        // Ajoutez des assertions bas�es sur le comportement attendu
        assertNotNull(retrievedFactures);
        assertEquals(2, retrievedFactures.size());

        // Vous pouvez ajouter d'autres assertions en fonction de votre logique m�tier
    }

    @Test
    void testConvertirMontant() {
        // Cr�ez une facture de test avec une devise ayant un taux de change de 1.5
        Devise devise = new Devise();
        devise.setTaux(1.5F);
        Facture facture = new Facture("123", 100.0, "14-02-2021", new Client(), devise);

        // Appelez la m�thode � tester
        double montantConverti = factureMetier.convertirMontant(facture);

        // Ajoutez des assertions bas�es sur le comportement attendu
        assertEquals(150.0, montantConverti);

        // Vous pouvez ajouter d'autres assertions en fonction de votre logique m�tier
    }

    @Test
    void testSommeMontantFactureParClient() {

        Client c1 = new Client( "c1", "sofien", "ayedi", "sfax", "29544554", "sof@gmail.com");
        Client c2 = new Client( "c2", "hedi", "sahs", "sfax", "255555155", "sahs@gmail.com");
        List<Facture> factures = Arrays.asList(
                new Facture("123", 100.0, "14-02-2021", c1, new Devise()),
                new Facture("456", 200.0, "14-02-2021",c2, new Devise())
        );

        // Configurez le comportement du repository pour retourner la liste de factures
        Mockito.when(factureRepository.findAllByClientId(1L)).thenReturn(
                factures.stream()
                        .filter(facture -> c1.getId().equals(facture.getClient().getId()))
                        .collect(Collectors.toList())
        );

        Mockito.when(factureRepository.findAllByClientId(c2.getId())).thenReturn(
                factures.stream()
                        .filter(facture -> c2.getId().equals(facture.getClient().getId()))
                        .collect(Collectors.toList())
        );

        double sommeMontantClient1 = factureMetier.sommeMontantFactureParClient(c1.getId());
        double sommeMontantClient2 = factureMetier.sommeMontantFactureParClient(c2.getId());

        assertEquals(100.0, sommeMontantClient1);
        assertEquals(200.0, sommeMontantClient2);
    }

}
