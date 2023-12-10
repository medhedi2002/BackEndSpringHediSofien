package soa.metier;

import soa.entities.Client;

import java.util.List;

public interface ClientMetierInterface {
    // Method to retrieve a list of all clients
    List<Client> listeClients();

    // Method to retrieve a specific client by ID
    Client getClientById(String clientId);

    // Method to save a new client
    void saveClient(Client client);

    // Method to update an existing client
    void ajouterClient(Client client);

    void updateClient(Client client);

    // Method to delete a client by ID
    void deleteClient(String clientId);


}
