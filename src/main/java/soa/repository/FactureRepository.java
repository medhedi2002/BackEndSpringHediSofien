package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soa.entities.Facture;

import java.util.List;


public interface FactureRepository extends JpaRepository<Facture, Long> {

    List<Facture> findAllByClientId(Long clientId);
}
