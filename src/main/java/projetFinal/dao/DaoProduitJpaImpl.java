package projetFinal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import projetFinal.Context;
import projetFinal.entity.Plat;

public class DaoProduitJpaImpl implements DaoProduit {

	@Override
	public List<Plat> findAll() {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		TypedQuery<Plat> query = em.createQuery("from Produit o", Plat.class);
		List<Plat> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public Plat findByKey(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Plat obj = em.find(Plat.class, key);
		em.close();
		return obj;
	}

	@Override
	public void insert(Plat obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(obj);
		tx.commit();
		em.close();

	}

	@Override
	public Plat update(Plat obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		obj = em.merge(obj);
		tx.commit();
		em.close();
		return obj;
	}

	@Override
	public void delete(Plat obj) {
		deleteByKey(obj.getId());
	}

	@Override
	public void deleteByKey(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Plat produit = em.find(Plat.class, key);
		produit.getLignesCommandes().forEach(lc -> {
			em.remove(lc);
		});
		em.remove(produit);
		tx.commit();
		em.close();
	}

}
