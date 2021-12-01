package fr.projetFormation.Hashi.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projetFormation.Hashi.entity.Cuisinier;
import fr.projetFormation.Hashi.entity.Gerant;
import fr.projetFormation.Hashi.entity.Plat;
import fr.projetFormation.Hashi.entity.Restaurant;
import fr.projetFormation.Hashi.exceptions.GerantException;
import fr.projetFormation.Hashi.exceptions.RestaurantException;
import fr.projetFormation.Hashi.repositories.CuisinierRepository;
import fr.projetFormation.Hashi.repositories.GerantRepository;
import fr.projetFormation.Hashi.repositories.LigneCarteRepository;
import fr.projetFormation.Hashi.repositories.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private LigneCarteRepository ligneCarteRepository;
	@Autowired
	private GerantRepository gerantRepository;
	@Autowired
	private CuisinierRepository cuisinierRepository;
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
		restaurant = byId(restaurant.getId());
		ligneCarteRepository.deleteByRestaurant(restaurant);

		List<Cuisinier> cuisiniers = cuisinierRepository.findAll();
		for (Cuisinier c : cuisiniers){
			if (c.getRestaurant().equals(restaurant))
			{
				c.setRestaurant(null);
				cuisinierRepository.save(c);
			}
		}

		List<Gerant> gerants = gerantRepository.findAllWithRestaurants().orElseThrow(GerantException::new);
		for (Gerant g : gerants){
			Set<Restaurant> restos = g.getRestaurants();
			if(restos.remove(restaurant)){
				g.setRestaurants(restos);
				gerantRepository.save(g);
			}
		}
		restaurantRepository.delete(restaurant);
	}

	public void removeOneLigneCarte(Restaurant restaurant, Plat plat) {
		restaurant = byId(restaurant.getId());
		ligneCarteRepository.deleteByRestaurantAndPlat(restaurant, plat);
	}

	public Restaurant byId(Long id) {
		return restaurantRepository.findById(id).orElseThrow(RestaurantException::new);
	}

	public List<Restaurant> all() {
		return restaurantRepository.findAll();
	}

	public Restaurant byIdWithLigneCarte(long id) {
		return restaurantRepository.findByIdWithLigneCarte(id).orElseThrow(RestaurantException::new);
	}

	public List<Restaurant> byNom(String nom) {
		return restaurantRepository.findByNomContaining(nom);
	}

	public List<Restaurant> byVilleOrCodePostal(String entry) {
		return restaurantRepository.findByVilleOrCodePostal("%" + entry + "%");
	}
	// public Page<Commande> clientFirstPage(int size) {
	// Pageable pageable = PageRequest.of(0, size);
	// return restaurantRepository.findAll(pageable);
	// }
	//
	// public Page<Commande> clientNextPage(Page<Commande> page) {
	// return restaurantRepository.findAll(page.nextOrLastPageable());
	// }
	//
	// public Page<Commande> clientPreviousPage(Page<Commande> page) {
	// return restaurantRepository.findAll(page.previousOrFirstPageable());
	// }
}