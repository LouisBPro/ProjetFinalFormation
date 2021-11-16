package projetFinal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import projetFinal.Context;
import projetFinal.entity.Client;

public class DaoClientJpaImpl implements DaoClient {

	@Override
	public List<Client> findAll() {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		TypedQuery<Client> query = em.createQuery("from Client o", Client.class);
		List<Client> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public Client findByKey(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Client obj = em.find(Client.class, key);
		em.close();
		return obj;
	}

	@Override
	public void insert(Client obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(obj);
		tx.commit();
		em.close();
	}

	@Override
	public Client update(Client obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		obj = em.merge(obj);
		tx.commit();
		em.close();
		return obj;

	}

	@Override
	public void delete(Client obj) {
		deleteByKey(obj.getId());
	}

	@Override
	public void deleteByKey(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Client objManaged = em.find(Client.class, key);
		objManaged.getCommandes().forEach(commande -> {
			commande.setClient(null);
			em.merge(commande);
		});
		em.remove(objManaged);
		tx.commit();
		em.close();
	}

	public Client findByKeyWithCommandes(Long key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		TypedQuery<Client> query = em.createQuery("select c from Client c left join fetch c.commandes where c.id=:key",
				Client.class);
		query.setParameter("key", key);
		Client client = query.getSingleResult();
		em.close();
		return client;
	}

	public List<Client> findAllWithCommandes() {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		TypedQuery<Client> query = em.createQuery("select distinct c from Client c left join fetch c.commandes", Client.class);
		List<Client> clients = query.getResultList();
		em.close();
		return clients;
	}
	
	// 2 olivier gozlan 1   =>Client(Commandes)
	// 2 olivier gozlan 2	=>Client
	// 3 Mathieu 		3	=>Client
	// 4 Louis	... 		=>Client	
}
