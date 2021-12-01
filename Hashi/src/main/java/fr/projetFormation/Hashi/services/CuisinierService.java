package fr.projetFormation.Hashi.services;

import java.util.Arrays;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projetFormation.Hashi.entities.Client;
import fr.projetFormation.Hashi.entities.Cuisinier;
import fr.projetFormation.Hashi.entities.Role;
import fr.projetFormation.Hashi.entities.User;
import fr.projetFormation.Hashi.exceptions.ClientException;
import fr.projetFormation.Hashi.exceptions.CuisinierException;
import fr.projetFormation.Hashi.repositories.CuisinierRepository;

@Service
public class CuisinierService {
	
	@Autowired
	private CuisinierRepository cuisinierRepository;

	public Cuisinier save(Cuisinier cuisinier) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Cuisinier>> violations = validator.validate(cuisinier);
		if (violations.isEmpty()) {
			User user = cuisinier.getUser();
			user.setPassword(user.getPassword());
			user.setRoles(Arrays.asList(Role.ROLE_CUISINIER));
			cuisinier.setUser(user);
			return cuisinierRepository.save(cuisinier);
		} else {
			throw new ClientException();
		}
	}

	public void delete(Cuisinier cuisinier) {
		cuisinierRepository.delete(cuisinier);
	}
	public void delete(Long id) {
		delete(cuisinierRepository.findById(id).orElseThrow(ClientException::new));
	}

	public Cuisinier byId(Long id) {
		return cuisinierRepository.findById(id).orElseThrow(CuisinierException::new);
	}
	
	//unnecessary
//	public Cuisinier byLoginOrEmail(String login) {
//		return cuisinierRepository.findByLoginOrEmail(login).orElseThrow(CuisinierException::new);
//	}

}
