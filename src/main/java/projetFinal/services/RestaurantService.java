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
import projetFinal.exceptions.RestaurantException;
import projetFinal.repositories.CommandeRepository;
import projetFinal.repositories.LigneCarteRepository;
import projetFinal.repositories.LigneCommandeRepository;
import projetFinal.repositories.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private LigneCarteRepository ligneCarteRepository;
	@Autowired
	private Validator validator;

	public void save(Restaurant restaurant) {
		Set<ConstraintViolation<Restaurant>> violations = validator.validate(restaurant);
		if (violations.isEmpty()) {
			restaurantRepository.save(restaurant);
			ligneCarteRepository.saveAll(restaurant.getLignesCarte());
		} else {
			throw new RestaurantException();
		}
	}

	public void delete(Restaurant restaurant) {
		ligneCarteRepository.deleteByRestaurant(restaurant);
		restaurantRepository.delete(restaurant);
	}

	public void removeOneLigneCarte(Restaurant restaurant, Plat plat) {
		ligneCarteRepository.deleteByRestaurantAndPlat(restaurant, plat);
	}

    public List<Restaurant> all(){
        return restaurantRepository.findAll();
    }
	
    public Restaurant byIdWithLigneCarte(long id){
        return restaurantRepository.findByIdWithLigneCarte(id).orElseThrow(RestaurantException::new);
    }
	
    public List<Restaurant> byNom(String nom){
        return restaurantRepository.findByNom(nom);
    }

    public List<Restaurant> byVille(String entry){
        return restaurantRepository.findByVilleOrCodePostal(entry);
    }
//	public Page<Commande> clientFirstPage(int size) {
//		Pageable pageable = PageRequest.of(0, size);
//		return restaurantRepository.findAll(pageable);
//	}
//
//	public Page<Commande> clientNextPage(Page<Commande> page) {
//		return restaurantRepository.findAll(page.nextOrLastPageable());
//	}
//
//	public Page<Commande> clientPreviousPage(Page<Commande> page) {
//		return restaurantRepository.findAll(page.previousOrFirstPageable());
//	}
}