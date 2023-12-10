package soa;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import soa.entities.Client;
import soa.metier.ClientMetierImpl;
import soa.repository.ClientRepository;
import java.util.*;

@SpringBootTest
class ClientTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientMetierImpl clientMetier;

    @Test
    void testListeClients() {
        // Mock the behavior of the repository
        List<Client> mockClients = Arrays.asList(
                new Client( "C1", "John", "Doe", "Address1", "123456789", "john.doe@example.com"),
                new Client( "C2", "Jane", "Doe", "Address2", "987654321", "jane.doe@example.com")
        );
        Mockito.when(clientRepository.findAll()).thenReturn(mockClients);

        // Call the method you want to test
        List<Client> clients = clientMetier.listeClients();

        // Add assertions based on the expected behavior
        assert clients != null;
        assert clients.size() == 2;
        // Add more specific assertions based on your requirements
    }

    @Test
    void testGetClientById() {
        // Mock the behavior of the repository
        Long clientId = 1L;
        Client mockClient = new Client(clientId, "C1", "John", "Doe", "Address1", "123456789", "john.doe@example.com");
        Mockito.when(clientRepository.findById(clientId)).thenReturn(Optional.of(mockClient));

        // Call the method you want to test
        Client client = clientMetier.getClientById(String.valueOf(clientId));

        // Add assertions based on the expected behavior
        assert client != null;
        assert client.getId().equals(clientId);
        // Add more specific assertions based on your requirements
    }


    @Test
    void testUpdateClient() {
        // Mock the behavior of the repository
        Long existingClientId = 1L;
        Client existingClient = new Client(existingClientId, "C1", "John", "Doe", "Address1", "123456789", "john.doe@example.com");
        Mockito.when(clientRepository.existsById(existingClientId)).thenReturn(true);

        // Call the method you want to test
        clientMetier.updateClient(existingClient);

        // Add assertions based on the expected behavior after updating a client
        Mockito.verify(clientRepository, Mockito.times(1)).save(existingClient);
        // Add more specific assertions based on your requirements
    }

    @Test
    void testDeleteClient() {
        // Mock the behavior of the repository
        Long clientIdToDelete = 1L;

        // Call the method you want to test
        clientMetier.deleteClient(String.valueOf(clientIdToDelete));

        // Add assertions based on the expected behavior after deleting a client
        Mockito.verify(clientRepository, Mockito.times(1)).deleteById(clientIdToDelete);
        // Add more specific assertions based on your requirements
    }

    @Test
    void testAjouterClient() {
        // Mock the behavior of the repository
        Client clientToAjouter = new Client(null, "C4", "Another", "Client", "Another Address", "123456789", "another.client@example.com");

        // Call the method you want to test
        clientMetier.ajouterClient(clientToAjouter);

        // Add assertions based on the expected behavior after adding a client
        Mockito.verify(clientRepository, Mockito.times(1)).save(clientToAjouter);
        // Add more specific assertions based on your requirements
    }

    // Similar tests for saveClient, updateClient, deleteClient, and ajouterClient methods...
}
