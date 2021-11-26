package projetFinal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import projetFinal.entity.Client;
import projetFinal.entity.Commande;
import projetFinal.entity.Restaurant;
import projetFinal.entity.Statut;

public interface CommandeRepository extends JpaRepository<Commande, Long>{
	
	
	@Query("select c from Commande c left join fetch c.lignesCommande where c.client=:client")
	List<Commande> findByClientWithLignesCommande(@Param("client") Client client);
	
	List<Commande> findByRestaurant(Restaurant restaurant);
	
	List<Commande> findByRestaurantAndStatut(Restaurant restaurant, Statut statut);
	
	@Transactional
	@Query("select c from Commande c left join fetch c.lignesCommande where c.id =:id")
	Optional<Commande> findByIdWithLignesCommande(@Param("id")Long id);
	
	@Transactional
	@Modifying
	@Query("update Commande c set c.client=null where c.client=:client")
	void removeClientFromCommandeByClient(@Param("client") Client client);
}
