package fr.projetFormation.Hashi.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projetFormation.Hashi.entities.Plat;
import fr.projetFormation.Hashi.exceptions.PlatException;
import fr.projetFormation.Hashi.repositories.LigneCarteRepository;
import fr.projetFormation.Hashi.repositories.LigneCommandeRepository;
import fr.projetFormation.Hashi.repositories.PlatRepository;

@Service
public class PlatService {
	@Autowired
	private PlatRepository platRepository;
	@Autowired
	private Validator validator;
	@Autowired
	private LigneCommandeRepository ligneCommandeRepository;
	@Autowired
	private LigneCarteRepository ligneCarteRepository;

	public Plat save(Plat plat) {
		Set<ConstraintViolation<Plat>> violations = validator.validate(plat);
		if (violations.isEmpty()) {
			platRepository.save(plat);
			return plat;
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


