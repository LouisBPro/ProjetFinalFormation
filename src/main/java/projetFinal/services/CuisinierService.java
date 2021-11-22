package projetFinal.services;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetFinal.entity.Cuisinier;
import projetFinal.exceptions.ClientException;
import projetFinal.exceptions.CuisinierException;
import projetFinal.repositories.CuisinierRepository;

@Service
public class CuisinierService {
	
	@Autowired
	private CuisinierRepository cuisinierRepository;

	public void save(Cuisinier cuisinier) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Cuisinier>> violations = validator.validate(cuisinier);
		if (violations.isEmpty()) {
			cuisinierRepository.save(cuisinier);
		} else {
			throw new ClientException();
		}
	}

	public void delete(Cuisinier cuisinier) {
		cuisinierRepository.delete(cuisinier);
	}

	public Cuisinier byId(Long id) {
		return cuisinierRepository.findByIdWithCommandes(id).orElseThrow(CuisinierException::new);
	}
	
	public Cuisinier byLoginOrEmail(String login) {
		return cuisinierRepository.findByLoginOrEmail(login).orElseThrow(CuisinierException::new);
	}

}
