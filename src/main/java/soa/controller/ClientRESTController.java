package soa.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.entities.Client;
import soa.metier.ClientMetierInterface;
import soa.repository.ClientRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/clients",produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientRESTController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMetierInterface clientMetier;

    @JsonIgnore
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
    @GetMapping(value="/{clientId}")
            public ResponseEntity<Client> getClientById(@PathVariable String clientId) {
        Client client = clientMetier.getClientById(clientId);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/ajouter")
    public ResponseEntity<String> ajouterClient(@RequestBody Client client) {
        clientMetier.ajouterClient(client);
        return new ResponseEntity<>("Client ajouté avec succès!", HttpStatus.CREATED);
    }

    @PutMapping("/modifier")
    public ResponseEntity<String> modifierClient(@RequestBody Client client) {
        try {
            clientMetier.updateClient(client);
            return new ResponseEntity<>("Client modifié avec succès!", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/supprimer/{clientId}")
    public ResponseEntity<String> supprimerClient(@PathVariable String clientId) {
        clientMetier.deleteClient(clientId);
        return new ResponseEntity<>("Client supprimé avec succès!", HttpStatus.OK);
    }

    // Your existing accueil() method - not mapped to a URL
}
