package fr.projetFormation.Hashi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fr.projetFormation.Hashi.entity.Commande;
import fr.projetFormation.Hashi.entity.Plat;
import fr.projetFormation.Hashi.entity.LigneCommande;
import fr.projetFormation.Hashi.LigneCommandePK;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, LigneCommandePK>{

	@Modifying
	@Transactional
	@Query("delete from LigneCommande lc where lc.id.commande=:commande")
	void deleteByCommande(@Param("commande") Commande commande);

	@Modifying
	@Transactional
	@Query("delete from LigneCommande lc where lc.id.plat=:plat")
	void deleteByPlat(@Param("plat") Plat plat);

	@Modifying
	@Transactional
	@Query("delete from LigneCommande lc where lc.id.plat=:plat and lc.id.commande=:commande")
	void deleteByCommandeAndPlat(@Param("commande") Commande commande, @Param("plat") Plat plat);
}
