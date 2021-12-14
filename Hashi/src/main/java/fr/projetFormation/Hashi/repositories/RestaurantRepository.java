package fr.projetFormation.Hashi.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.projetFormation.Hashi.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Transactional
    @Query("select r from Restaurant r left join fetch r.lignesCarte where r.id =:id")
    Optional<Restaurant> findByIdWithLigneCarte(@Param("id") Long id);

    List<Restaurant> findByNomContaining(String nom);

    @Query("select r from Restaurant r where (r.adresse.ville LIKE :entry) OR (r.adresse.codePostal LIKE :entry)")
    List<Restaurant> findByVilleOrCodePostal(@Param("entry") String entry);

    // tous les restaurants n'ayant pas de gerant
	@Query("select r from Restaurant r where r.gerant=null")
	List<Restaurant> findAllAvailable();
}
