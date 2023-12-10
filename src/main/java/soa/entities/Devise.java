package soa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import soa.entities.Facture;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Devise {

    @Id
    @GeneratedValue
    private Long id;

    private String code;
    private String symbole;
    private float taux;

    @OneToMany(mappedBy = "devise", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Facture> factures = new ArrayList<>();

    // Constructors

    public Devise() {
        // Default constructor
    }

    public Devise(String code, String symbole, float taux) {
        this.code = code;
        this.symbole = symbole;
        this.taux = taux;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Devise devise = (Devise) o;
        return Float.compare(devise.taux, taux) == 0 &&
                Objects.equals(id, devise.id) &&
                Objects.equals(code, devise.code) &&
                Objects.equals(symbole, devise.symbole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, symbole, taux);
    }


    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    public float getTaux() {
        return taux;
    }

    public void setTaux(float taux) {
        this.taux = taux;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    // toString

    @Override
    public String toString() {
        return "Devise{" +
                " id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", symbole='" + symbole + '\'' +
                ", taux=" + taux +
                '}';
    }
}
