package fr.projetFormation.Hashi.repositories;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.projetFormation.Hashi.entities.Cuisinier;
import fr.projetFormation.Hashi.entities.Restaurant;

public interface CuisinierRepository extends JpaRepository<Cuisinier, Long>{
	
//	@Query("select c from Cuisinier c where c.login=:login or c.email=:login")
//	Optional<Cuisinier> findByLoginOrEmail(@Param("login") String login);

    @Modifying
	@Transactional
	@Query("delete from Cuisinier c where c.restaurant=:restaurant")
	void deleteByRestaurant(@Param("restaurant") Restaurant restaurant);

	// tous les cuisiniers n'ayant pas de restaurant
	@Query("select c from Cuisinier c where c.restaurant=null")
	List<Cuisinier> findAllAvailable();
}
