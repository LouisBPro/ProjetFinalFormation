package projetFinal.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import projetFinal.entity.Client;
import projetFinal.entity.Commande;
import projetFinal.entity.Plat;
import projetFinal.entity.Restaurant;
import projetFinal.entity.Statut;
import projetFinal.exceptions.CommandeException;
import projetFinal.repositories.CommandeRepository;
import projetFinal.repositories.LigneCommandeRepository;

@Service
public class CommandeService {

	@Autowired
	private CommandeRepository commandeRepository;
	@Autowired
	private LigneCommandeRepository ligneCommandeRepository;
	@Autowired
	private Validator validator;

	public void save(Commande commande) {
		Set<ConstraintViolation<Commande>> violations = validator.validate(commande);
		if (violations.isEmpty()) {
			commandeRepository.save(commande);
			ligneCommandeRepository.saveAll(commande.getLignesCommande());
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
	
	public List<Commande> byClientWithLigneCommande(Client client){
		return commandeRepository.findByClientWithLignesCommande(client);
	}
	
	public List<Commande> byRestaurantAndStatut(Restaurant restaurant, Statut statut){
		return commandeRepository.findByRestaurantAndStatut(restaurant, statut);
	}
	
	public List<Commande> byRestaurant(Restaurant restaurant){
		return commandeRepository.findByRestaurant(restaurant);
	}
	
	public Commande byIdWithLigneCommande(Long id) {
		return commandeRepository.findByIdWithLignesCommande(id).orElseThrow(CommandeException::new);
	}
	
//	public Page<Commande> clientFirstPage(int size) {
//		Pageable pageable = PageRequest.of(0, size);
//		return commandeRepository.findAll(pageable);
//	}
//
//	public Page<Commande> clientNextPage(Page<Commande> page) {
//		return commandeRepository.findAll(page.nextOrLastPageable());
//	}
//
//	public Page<Commande> clientPreviousPage(Page<Commande> page) {
//		return commandeRepository.findAll(page.previousOrFirstPageable());
//	}
}