package fr.projetFormation.Hashi.restcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.projetFormation.Hashi.entities.Cuisinier;
import fr.projetFormation.Hashi.entities.JsonViews;
import fr.projetFormation.Hashi.entities.User;
import fr.projetFormation.Hashi.services.CuisinierService;

@RestController
@RequestMapping("/api/cuisinier")
@CrossOrigin(origins="*")
public class CuisinierRestController {

	@Autowired
	private CuisinierService cuisinierService;

	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Cuisinier byId(@PathVariable("id") Long id) {
		return cuisinierService.byId(id);
	}

	@PostMapping("")
	@JsonView(JsonViews.Common.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cuisinier create(@Valid @RequestBody Cuisinier cuisinier, BindingResult br) {
		return cuisinierService.create(cuisinier);
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Cuisinier update(@Valid @RequestBody Cuisinier cuisinier, BindingResult br, @PathVariable("id") Long id) {
		Cuisinier cuisinierEnBase = cuisinierService.byId(id);
		cuisinierEnBase.setNom(cuisinier.getNom());
		cuisinierEnBase.setPrenom(cuisinier.getPrenom());
        cuisinierEnBase.setEmail(cuisinier.getEmail());
		return cuisinierService.create(cuisinierEnBase);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		cuisinierService.delete(id);
	}
}
