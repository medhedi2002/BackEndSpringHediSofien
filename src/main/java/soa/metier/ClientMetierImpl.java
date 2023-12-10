package soa.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.entities.Client;
import soa.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientMetierImpl implements ClientMetierInterface {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientMetierImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> listeClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(String clientId) {
        try {
            Long id = Long.parseLong(clientId);
            Optional<Client> optionalClient = clientRepository.findById(id);
            return optionalClient.orElse(null);
        } catch (NumberFormatException e) {
            // Handle the case where clientId is not a valid Long
            return null;
        }
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }


    @Override
    public void updateClient(Client client) {
        // Check if the client exists before updating
        if (clientRepository.existsById(client.getId())) {
            clientRepository.save(client);
        } else {
            // Handle the case when the client does not exist
            throw new IllegalArgumentException("Client with ID " + client.getId() + " does not exist");
        }
    }

    @Override
    public void deleteClient(String clientId) {
        clientRepository.deleteById(Long.valueOf(clientId));
    }
    public void ajouterClient(Client client) {
        // Add your custom logic, if needed, before saving the client
        clientRepository.save(client);
    }

}
