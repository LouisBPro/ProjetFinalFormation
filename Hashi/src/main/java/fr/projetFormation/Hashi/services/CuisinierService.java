package fr.projetFormation.Hashi.services;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.projetFormation.Hashi.entities.Cuisinier;
import fr.projetFormation.Hashi.entities.Role;
import fr.projetFormation.Hashi.entities.User;
import fr.projetFormation.Hashi.exceptions.ClientException;
import fr.projetFormation.Hashi.exceptions.CuisinierException;
import fr.projetFormation.Hashi.repositories.CuisinierRepository;
import fr.projetFormation.Hashi.repositories.UserRepository;

@Service
public class CuisinierService {

	@Autowired
	private CuisinierRepository cuisinierRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Cuisinier create(Cuisinier cuisinier) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Cuisinier>> violations = validator.validate(cuisinier);
		if (violations.isEmpty()) {
			User user = cuisinier.getUser();
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoles(Arrays.asList(Role.ROLE_CUISINIER));
			user.setEnable(true);
			userRepository.save(user);
			cuisinier = cuisinierRepository.save(cuisinier);
			return cuisinier;
		} else {
			throw new ClientException();
		}
	}

	public Cuisinier update(Cuisinier cuisinier) {
		return cuisinierRepository.save(cuisinier);
	}

	public void delete(Cuisinier cuisinier) {
		Cuisinier cuisinierEnBase = cuisinierRepository.findById(cuisinier.getId())
				.orElseThrow(CuisinierException::new);
		cuisinierRepository.delete(cuisinierEnBase);
		userRepository.delete(cuisinierEnBase.getUser());
	}

	public void delete(Long id) {
		delete(cuisinierRepository.findById(id).orElseThrow(CuisinierException::new));
	}

	public Cuisinier byId(Long id) {
		return cuisinierRepository.findById(id).orElseThrow(CuisinierException::new);
	}
	
	// Tous les cuisiniers qui n'ont pas de restaurant
	public List<Cuisinier> allAvailable() {
		return cuisinierRepository.findAllAvailable();
	}

	// unnecessary
	// public Cuisinier byLoginOrEmail(String login) {
	// return
	// cuisinierRepository.findByLoginOrEmail(login).orElseThrow(CuisinierException::new);
	// }

}
