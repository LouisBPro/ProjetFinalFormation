package projetFinal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projetFinal.entity.Client;
import projetFinal.entity.Commande;
import projetFinal.entity.Restaurant;
import projetFinal.entity.Statut;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

    @Query("select r from Restaurant left join fetch r.lignesCarte where r.id =:id")
	Optional<Restaurant> findByIdWithLigneCarte(@Param("id")Long id);
    
    List<Restaurant> findByNom(String nom);

    @Query("select r from Restaurant r where (r.ville LIKE '%:entry%') OR (r.codePostal LIKE '%:entry%')")
    List<Restaurant> findByVilleOrCodePostal(@Param("entry") String entry);
}
