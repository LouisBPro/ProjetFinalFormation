package projetFinal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projetFinal.entity.Cuisinier;

public interface CuisinierRepository extends JpaRepository<Cuisinier, Long>{

	Cuisinier findById();
	
	@Query("select c from Cuisinier c where c.login=:login or c.email=:login")
	Optional<Cuisinier> findByLoginOrEmail(@Param("login") String login);

}
