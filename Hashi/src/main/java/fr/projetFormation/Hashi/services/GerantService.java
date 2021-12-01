package fr.projetFormation.Hashi.services;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projetFormation.Hashi.entities.Gerant;
import fr.projetFormation.Hashi.exceptions.ClientException;
import fr.projetFormation.Hashi.exceptions.GerantException;
import fr.projetFormation.Hashi.repositories.GerantRepository;

@Service
public class GerantService {
	
	@Autowired
	private GerantRepository gerantRepository;

	public void save(Gerant gerant) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Gerant>> violations = validator.validate(gerant);
		if (violations.isEmpty()) {
			gerantRepository.save(gerant);
		} else {
			throw new ClientException();
		}
	}

	public void delete(Gerant gerant) {
		gerantRepository.delete(gerant);
	}

	public Gerant byId(Long id) {
		return gerantRepository.findById(id).orElseThrow(GerantException::new);
	}
	
	public Gerant byIdWithRestaurants(Long id) {
		return gerantRepository.findByIdWithRestaurants(id).orElseThrow(GerantException::new);
	}

//	public Gerant byLoginOrEmail(String login) {
//		return gerantRepository.findByLoginOrEmail(login).orElseThrow(GerantException::new);
//	}


}
