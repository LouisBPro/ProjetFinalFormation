package projetFinal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import projetFinal.entity.Plat;

public interface PlatRepository extends JpaRepository<Plat,Long> {
	@Transactional
	List<Plat> findByNomContaining(String nom);

}
