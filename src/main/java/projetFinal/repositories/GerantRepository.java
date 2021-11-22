package projetFinal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projetFinal.entity.Gerant;

public interface GerantRepository extends JpaRepository<Gerant, Long>{
	
	@Query("select g from Gerant g where g.login=:login or g.email=:login")
	Optional<Gerant> findByLoginOrEmail(@Param("login") String login);

}
