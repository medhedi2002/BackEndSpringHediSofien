package soa.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soa.entities.Client;
import soa.entities.Devise;
import soa.entities.Facture;
import soa.metier.DeviseMetierInterface;
import soa.repository.DeviseRepository;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/devises",produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviseRESTController {

    @Autowired
    private DeviseRepository deviseRepository;

    @Autowired
    private DeviseMetierInterface deviseMetier;

    @GetMapping
    public ResponseEntity<List<Devise>> getAllDevises() {
        List<Devise> devises = deviseRepository.findAll();

        return new ResponseEntity<>(devises, HttpStatus.OK);
    }
    @GetMapping(value="/{deviseId}")
    public ResponseEntity<Devise> getDeviseById(@PathVariable String deviseId) {
        Devise devise = deviseMetier.getDeviseById(deviseId);
        if (devise != null) {
            return new ResponseEntity<>(devise, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/ajouter")
    public String ajouterDevise(@RequestBody Devise devise) {
        deviseMetier.ajouterDevise(devise);
        return "Devise ajoutée avec succès!";
    }

    @PutMapping("/modifier")
    public String modifierDevise(@RequestBody Devise devise) {
        deviseMetier.updateDevise(devise);
        return "Devise modifiée avec succès!";
    }

    @DeleteMapping("/supprimer/{deviseId}")
    public String supprimerDevise(@PathVariable Long deviseId) {
        deviseMetier.deleteDevise(deviseId);
        return "Devise supprimée avec succès!";
    }

}
