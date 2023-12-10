package soa.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Facture {

    @Id
    @GeneratedValue
    private Long id;

    private String numero;
    private double montant;

    @Column(name = "date_f") // Use a specific column name to avoid conflicts with reserved words
    private String dateF; // Change the type to String

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "devise_id")
    private Devise devise;

    // Constructors
    public Facture(String numero, double montant, String dateF, Client client, Devise devise) {
        this.numero = numero;
        this.montant = montant;
        this.dateF = dateF;
        this.client = client;
        this.devise = devise;
    }

    // (Other constructors and methods)
    public Facture() {}

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDateF() {
        return dateF;
    }

    public void setDateF(String dateF) {
        this.dateF = dateF;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    // toString

    @Override
    public String toString() {
        return "Facture{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", montant=" + montant +
                ", dateF='" + dateF + '\'' +
                ", client=" + client +
                ", devise=" + devise +
                '}';
    }
}
