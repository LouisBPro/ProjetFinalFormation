package projetFinal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import projetFinal.Context;
import projetFinal.entity.Commande;

public class DaoCommandeJpaImpl implements DaoCommande {

	@Override
	public List<Commande> findAll() {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		TypedQuery<Commande> query = em.createQuery("from Commande o", Commande.class);
		List<Commande> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public Commande findByKey(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Commande obj = em.find(Commande.class, key);
		em.close();
		return obj;
	}

	@Override
	public void insert(Commande obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(obj);
		obj.getLignesCommandes().forEach(lc -> {
			em.persist(lc);
		});
		tx.commit();
		em.close();

	}

	@Override
	public Commande update(Commande obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		obj = em.merge(obj);
		tx.commit();
		em.close();
		return obj;
	}

	@Override
	public void delete(Commande obj) {
		deleteByKey(obj.getNumero());

	}

	@Override
	public void deleteByKey(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Commande commande = em.find(Commande.class, key);
//		commande.getLignesCommandes().forEach(lc -> {
//			em.remove(lc);
//		});

		Query deleteLigneCommande = em.createQuery("delete from LigneCommande lc where lc.id.commande.numero=:numero");
		deleteLigneCommande.setParameter("numero", key);
		deleteLigneCommande.executeUpdate();
		em.remove(commande);
		tx.commit();
		em.close();
	}

	public Commande findByKeyWithLignesCommandes(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		TypedQuery<Commande> query = em.createNamedQuery("Commande.findByKeyWithLignesCommandes", Commande.class);
		query.setParameter("numero", key);
		// Commande commande = query.getSingleResult();
		Commande commande = null;
		List<Commande> list = query.getResultList();
		if (!list.isEmpty()) {
			commande = list.get(0);
		}
		em.close();
		return commande;
	}

	public List<Commande> findAllWithLignesCommandes() {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		TypedQuery<Commande> query = em.createNamedQuery("Commande.findAllWithLignesCommandes", Commande.class);
		List<Commande> list = query.getResultList();
		em.close();
		return list;
	}

}
