package projetFinal.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;

import projetFinal.entity.Plat;
import projetFinal.exceptions.PlatException;
import projetFinal.repositories.LigneCarteRepository;
import projetFinal.repositories.LigneCommandeRepository;
import projetFinal.repositories.PlatRepository;

public class PlatService {
	@Autowired
	private PlatRepository platRepository;
	@Autowired
	private Validator validator;
	@Autowired
	private LigneCommandeRepository ligneCommandeRepository;
	@Autowired
	private LigneCarteRepository ligneCarteRepository;

	public void save(Plat plat) {
		Set<ConstraintViolation<Plat>> violations = validator.validate(plat);
		if (violations.isEmpty()) {
			platRepository.save(plat);
		} else {
			throw new PlatException();
		}

	}

	public void delete(Plat plat) {
		plat = byId(plat.getId());
		ligneCarteRepository.deleteByPlat(plat);
		ligneCommandeRepository.deleteByPlat(plat);
		platRepository.delete(plat);
	}

	public Plat byId(Long id) {
		return platRepository.findById(id).orElseThrow(PlatException::new);
	}

	public List<Plat> all() {
		return platRepository.findAll();
	}

	public List<Plat> byNom(String nom) {
		return platRepository.findByNomContaining(nom);
	}
}


