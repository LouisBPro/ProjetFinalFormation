package projetFinal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import projetFinal.Context;
import projetFinal.entity.Produit;

public class DaoProduitJpaImpl implements DaoProduit {

	@Override
	public List<Produit> findAll() {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		TypedQuery<Produit> query = em.createQuery("from Produit o", Produit.class);
		List<Produit> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public Produit findByKey(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Produit obj = em.find(Produit.class, key);
		em.close();
		return obj;
	}

	@Override
	public void insert(Produit obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(obj);
		tx.commit();
		em.close();

	}

	@Override
	public Produit update(Produit obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		obj = em.merge(obj);
		tx.commit();
		em.close();
		return obj;
	}

	@Override
	public void delete(Produit obj) {
		deleteByKey(obj.getId());
	}

	@Override
	public void deleteByKey(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Produit produit = em.find(Produit.class, key);
		produit.getLignesCommandes().forEach(lc -> {
			em.remove(lc);
		});
		em.remove(produit);
		tx.commit();
		em.close();
	}

}
