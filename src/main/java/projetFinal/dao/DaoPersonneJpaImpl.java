package projetFinal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import projetFinal.entity.Personne;
import projetFinal.Context;

class DaoPersonneJpaImpl implements DaoPersonne {

	@Override
	public List<Personne> findAll() {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		// requete jpql
		TypedQuery<Personne> query = em.createQuery("from Personne p", Personne.class);
		List<Personne> personnes = query.getResultList();
		em.close();
		return personnes;
	}

	@Override
	public Personne findByKey(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Personne personne = em.find(Personne.class, key);
		em.close();
		return personne;
	}

	@Override
	public void insert(Personne obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(obj);
		tx.commit();
		em.close();
	}

	@Override
	public Personne update(Personne obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		obj = em.merge(obj);
		tx.commit();
		em.close();
		return obj;
	}

	@Override
	public void delete(Personne obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(em.merge(obj));
		tx.commit();
		em.close();

	}

	@Override
	public void deleteByKey(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(em.find(Personne.class, key));
		tx.commit();
		em.close();

	}

}
