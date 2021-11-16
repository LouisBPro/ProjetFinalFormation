package projetFinal.dao;

import java.util.List;

import projetFinal.entity.Client;

public interface DaoClient extends DaoGeneric<Client, Long> {
	Client findByKeyWithCommandes(Long key);

	List<Client> findAllWithCommandes();
}
