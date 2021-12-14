package fr.projetFormation.Hashi.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projetFormation.Hashi.entities.Client;
import fr.projetFormation.Hashi.entities.Commande;
import fr.projetFormation.Hashi.entities.LigneCommande;
import fr.projetFormation.Hashi.entities.Plat;
import fr.projetFormation.Hashi.entities.Restaurant;
import fr.projetFormation.Hashi.entities.Statut;
import fr.projetFormation.Hashi.exceptions.CommandeException;
import fr.projetFormation.Hashi.repositories.CommandeRepository;
import fr.projetFormation.Hashi.repositories.LigneCommandeRepository;

@Service
public class CommandeService {

	@Autowired
	private CommandeRepository commandeRepository;
	@Autowired
	private LigneCommandeRepository ligneCommandeRepository;
	@Autowired
	private Validator validator;

	public Commande save(Commande commande) {
 		Set<ConstraintViolation<Commande>> violations = validator.validate(commande);
		if (violations.isEmpty()) {
			commandeRepository.save(commande);
			ligneCommandeRepository.saveAll(commande.getLignesCommande());
			return commande;
		} else {
			throw new CommandeException();
		}
	}

	public void delete(Commande commande) {
		ligneCommandeRepository.deleteByCommande(commande);
		commandeRepository.delete(commande);
	}

	public void removeOneLigneCommande(Commande commande, Plat plat) {
		ligneCommandeRepository.deleteByCommandeAndPlat(commande, plat);
	}

	public List<Commande> byClientWithLigneCommande(Client client) {
		return commandeRepository.findByClientWithLignesCommande(client);
	}

	public List<Commande> byRestaurantAndStatut(Restaurant restaurant, Statut statut) {
		return commandeRepository.findByRestaurantAndStatut(restaurant, statut);
	}

	public List<Commande> byRestaurant(Restaurant restaurant) {
		return commandeRepository.findByRestaurant(restaurant);
	}

	public Commande byIdWithLigneCommande(Long id) {
		return commandeRepository.findByIdWithLignesCommande(id).orElseThrow(CommandeException::new);
	}

	public Commande byId(Long id) {
		return commandeRepository.findById(id).orElseThrow(CommandeException::new);
	}
}