package projetFinal.dao;

import java.util.List;

import projetFinal.entity.Commande;

public interface DaoCommande extends DaoGeneric<Commande, Long> {
	Commande findByKeyWithLignesCommandes(Long key);

	List<Commande> findAllWithLignesCommandes();
}
