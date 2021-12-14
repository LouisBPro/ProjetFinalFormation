package fr.projetFormation.Hashi.services;

import java.util.Arrays;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.projetFormation.Hashi.entities.Gerant;
import fr.projetFormation.Hashi.entities.Restaurant;
import fr.projetFormation.Hashi.entities.Role;
import fr.projetFormation.Hashi.entities.User;
import fr.projetFormation.Hashi.exceptions.ClientException;
import fr.projetFormation.Hashi.exceptions.GerantException;
import fr.projetFormation.Hashi.repositories.GerantRepository;
import fr.projetFormation.Hashi.repositories.UserRepository;

@Service
public class GerantService {

	@Autowired
	private GerantRepository gerantRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RestaurantService restaurantService;

	public Gerant create(Gerant gerant) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Gerant>> violations = validator.validate(gerant);
		if (violations.isEmpty()) {
			User user = gerant.getUser();
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoles(Arrays.asList(Role.ROLE_GERANT));
			user.setEnable(true);
			userRepository.save(user);
			gerant = gerantRepository.save(gerant);
			return gerant;
		} else {
			throw new ClientException();
		}
	}

	public Gerant update(Gerant gerant) {
		return gerantRepository.save(gerant);
	}


	public void delete(Long id) {
		Gerant gerantEnBase = gerantRepository.findById(id).orElseThrow(GerantException::new);
		for (Restaurant restaurant : gerantEnBase.getRestaurants()){
			restaurantService.removeGerant(restaurant);
		}
		gerantRepository.delete(gerantEnBase);
		userRepository.delete(gerantEnBase.getUser());
	}

	public Gerant byId(Long id) {
		return gerantRepository.findById(id).orElseThrow(GerantException::new);
	}

	public Gerant byIdWithRestaurants(Long id) {
		return gerantRepository.findByIdWithRestaurants(id).orElseThrow(GerantException::new);
	}

	// public Gerant byLoginOrEmail(String login) {
	// return
	// gerantRepository.findByLoginOrEmail(login).orElseThrow(GerantException::new);
	// }

}
