package soa.dao;

import soa.entities.Devise;

import java.util.List;

public interface IDeviseDao {
	Devise save(Devise d);
	List<Devise> findAll();
	Devise findOne(Long id);
	Devise update(Devise d);
	void delete(Long id);

	List<Devise> findByNom(String nom);

	List<Devise> findByCodeAndNom(String code, String nom);
}
