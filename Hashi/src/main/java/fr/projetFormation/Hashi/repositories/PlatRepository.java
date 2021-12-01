package fr.projetFormation.Hashi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import fr.projetFormation.Hashi.entity.Plat;

public interface PlatRepository extends JpaRepository<Plat,Long> {
	@Transactional
	List<Plat> findByNomContaining(String nom);

}
