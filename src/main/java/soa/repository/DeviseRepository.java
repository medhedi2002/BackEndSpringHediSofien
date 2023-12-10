package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soa.entities.Devise;

@Repository
public interface DeviseRepository extends JpaRepository<Devise, Long> {


}
