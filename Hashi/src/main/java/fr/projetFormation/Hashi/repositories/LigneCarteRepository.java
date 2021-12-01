package fr.projetFormation.Hashi.repositories;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.projetFormation.Hashi.entity.LigneCarte;
import fr.projetFormation.Hashi.entity.Plat;
import fr.projetFormation.Hashi.entity.Restaurant;

public interface LigneCarteRepository extends JpaRepository<LigneCarte, Long>{
	@Modifying
	@Transactional
	@Query("delete from LigneCarte lc where lc.id.plat=:plat")
	void deleteByPlat(@Param("plat") Plat plat);
	
    @Modifying
	@Transactional
	@Query("delete from LigneCarte lc where lc.id.restaurant=:restaurant")
	void deleteByRestaurant(@Param("restaurant") Restaurant restaurant);
	
	@Modifying
	@Transactional
	@Query("delete from LigneCarte lc where lc.id.plat=:plat and lc.id.restaurant=:restaurant")
	void deleteByRestaurantAndPlat(@Param("restaurant") Restaurant restaurant, @Param("plat") Plat plat);
}