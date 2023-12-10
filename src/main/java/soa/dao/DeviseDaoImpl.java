package soa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import soa.entities.Devise;

import java.util.List;

@Repository
@Transactional
public class DeviseDaoImpl implements IDeviseDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Devise save(Devise d) {
		em.persist(d);
		return d;
	}

	@Override
	public List<Devise> findAll() {
		Query query = em.createQuery("select d from Devise d");
		return query.getResultList();
	}

	@Override
	public Devise findOne(Long id) {
		return em.find(Devise.class, id);
	}

	@Override
	public Devise update(Devise d) {
		return em.merge(d);
	}

	@Override
	public void delete(Long id) {
		Devise d = em.find(Devise.class, id);
		if (d != null) {
			em.remove(d);
		}
	}

	@Override
	public List<Devise> findByNom(String nom) {
		Query query = em.createQuery("select d from Devise d where d.nom = :nom");
		query.setParameter("nom", nom);
		return query.getResultList();
	}

	@Override
	public List<Devise> findByCodeAndNom(String code, String nom) {
		Query query = em.createQuery("select d from Devise d where d.code = :code and d.nom = :nom");
		query.setParameter("code", code);
		query.setParameter("nom", nom);
		return query.getResultList();
	}
}
