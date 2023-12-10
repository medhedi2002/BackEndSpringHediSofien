package soa.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import soa.entities.Facture;
import soa.metier.FactureMetierInterface;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/factures",produces = MediaType.APPLICATION_JSON_VALUE)
public class FactureRESTController {

    @Autowired
    private FactureMetierInterface factureMetier;



    @JsonIgnore
    @GetMapping
    public List<Facture> getAllFactures() {
        return factureMetier.listeFactures();
    }

    @PostMapping("/ajouter")
    public String ajouterFacture(@RequestBody Facture facture) {
        factureMetier.ajouterFacture(facture);
        return "Facture ajoutée avec succès!";
    }

    @PutMapping("/modifier")
    public String modifierFacture(@RequestBody Facture facture) {
        factureMetier.updateFacture(facture);
        return "Facture modifiée avec succès!";
    }

    @DeleteMapping("/supprimer/{factureNumber}")
    public String supprimerFacture(@PathVariable String factureNumber) {
        factureMetier.deleteFacture(Long.valueOf(factureNumber));
        return "Facture supprimée avec succès!";
    }

    @GetMapping("/getMontantTotalAvecTVA/{factureId}")
    public double getMontantTotalAvecTVA(@PathVariable String factureId) {
        Facture facture = factureMetier.getFactureById(Long.valueOf(factureId));
        return factureMetier.getMontantTotalAvecTVA(facture);
    }
    @GetMapping("/convertirMontant/{factureId}")
    public double convertirMontant(@PathVariable String factureId) {
        Facture facture = factureMetier.getFactureById(Long.valueOf(factureId));
        return factureMetier.convertirMontant(facture);
    }

    @GetMapping("/sommeMontantFactureParClient/{clientId}")
    public double sommeMontantFactureParClient(@PathVariable String clientId) {
        return factureMetier.sommeMontantFactureParClient(Long.valueOf(clientId));
    }
    // Add other methods as needed for your use case

}