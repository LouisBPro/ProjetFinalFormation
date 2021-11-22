package projetFinal.services;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetFinal.entity.Gerant;
import projetFinal.exceptions.ClientException;
import projetFinal.exceptions.GerantException;
import projetFinal.repositories.GerantRepository;

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
		return gerantRepository.findByIdWithCommandes(id).orElseThrow(GerantException::new);
	}
	
	public Gerant byLoginOrEmail(String login) {
		return gerantRepository.findByLoginOrEmail(login).orElseThrow(GerantException::new);
	}

}
