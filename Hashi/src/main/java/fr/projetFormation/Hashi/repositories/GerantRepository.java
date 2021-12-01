package fr.projetFormation.Hashi.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.projetFormation.Hashi.entities.Gerant;

public interface GerantRepository extends JpaRepository<Gerant, Long>{
	
	@Query("select g from Gerant g where g.login=:login or g.email=:login")
	Optional<Gerant> findByLoginOrEmail(@Param("login") String login);

	@Transactional
    @Query("select g from Gerant g left join fetch g.restaurants")
    Optional<List<Gerant>> findAllWithRestaurants();

	@Transactional
    @Query("select g from Gerant g left join fetch g.restaurants where g.id=:id")
    Optional<Gerant> findByIdWithRestaurants(@Param("id") Long id);
}
