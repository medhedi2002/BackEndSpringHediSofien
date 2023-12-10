package soa;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import soa.entities.Devise;
import soa.entities.Facture;
import soa.metier.DeviseMetierImpl;
import soa.repository.DeviseRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DeviseTest {

    @Mock
    private DeviseRepository deviseRepository;

    @InjectMocks
    private DeviseMetierImpl deviseMetier;




    @Test
    void testAjouterDevise() {
        // Mock the behavior of the repository
        Devise deviseToAdd = new Devise("USD", "$", 1.2F);

        // Call the method you want to test
        deviseMetier.ajouterDevise(deviseToAdd);

        // Add assertions based on the expected behavior after adding a devise
        Mockito.verify(deviseRepository, Mockito.times(1)).save(deviseToAdd);
        // Add more specific assertions based on your requirements
    }




    @Test
    void testDeleteDevise() {
        // Mock the behavior of the repository
        Long deviseIdToDelete = 1L;
        Mockito.when(deviseRepository.existsById(deviseIdToDelete)).thenReturn(true);

        // Call the method you want to test
        deviseMetier.deleteDevise(deviseIdToDelete);

        // Add assertions based on the expected behavior after deleting a devise
        Mockito.verify(deviseRepository, Mockito.times(1)).deleteById(deviseIdToDelete);
        // Add more specific assertions based on your requirements
    }

    @Test
    void testUpdateDevise() {
        // Create an existing devise without setting the ID in the constructor
        Devise existingDevise = new Devise("USD", "$", 1.2F);

        // Set the ID of the devise (simulating a persistent entity with an existing ID)
        existingDevise.setId(1L);

        // Simulate the repository behavior for an existing ID
        Mockito.when(deviseRepository.existsById(existingDevise.getId())).thenReturn(true);

        // When calling the save method of the repository, return the devise with the ID
        Mockito.when(deviseRepository.save(Mockito.any(Devise.class))).thenAnswer(invocation -> {
            Devise savedDevise = invocation.getArgument(0);

            // Check if ID is not null before setting it
            if (existingDevise.getId() != null) {
                savedDevise.setId(existingDevise.getId());
            }

            return savedDevise;
        });

        // Call the method to test
        deviseMetier.updateDevise(existingDevise);

        // Verify that the save method of the repository was called with the existing devise
        Mockito.verify(deviseRepository, Mockito.times(1)).save(existingDevise);

        // You can also verify that the ID was set after saving
        assertNotNull(existingDevise.getId());
    }

    @Test
    void testGetDeviseById() {
        // Mock the behavior of the repository
        Long existingDeviseId = 1L;
        Devise existingDevise = new Devise("USD", "$", 1.2F);
        existingDevise.setId(existingDeviseId);  // Set the ID to avoid null ID

        Mockito.when(deviseRepository.findById(existingDeviseId)).thenReturn(Optional.of(existingDevise));

        // Call the method you want to test
        Devise retrievedDevise = deviseMetier.getDeviseById(String.valueOf(existingDeviseId));

        // Add assertions based on the expected behavior
        assertNotNull(retrievedDevise);  // Ensure that the retrieved devise is not null
        assertEquals(existingDeviseId, retrievedDevise.getId());  // Verify that the IDs match
        // Add more specific assertions based on your requirements
    }



    @Test
    void testListeDevises() {
        // Mock the behavior of the repository
        List<Devise> mockDevises = Arrays.asList(
                new Devise("USD", "$", 1.2F),
                new Devise("EUR", "€", 1.0F)
        );
        Mockito.when(deviseRepository.findAll()).thenReturn(mockDevises);

        // Call the method you want to test
        List<Devise> devises = deviseMetier.listeDevises();

        // Add assertions based on the expected behavior
        assert devises != null;
        assert devises.size() == 2;
        // Add more specific assertions based on your requirements
    }
}
