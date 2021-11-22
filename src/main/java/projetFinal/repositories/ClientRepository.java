package projetFinal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projetFinal.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	
	@Query("select c from Client c left join fetch c.commandes where c.id=:id")
	Optional<Client> findByIdWithCommandes(@Param("id") Long id);
	
	@Query("select c form Client c where c.login=:login or c.email=:login")
	Optional<Client> findByLoginOrEmail(@Param("login") String login);

}
