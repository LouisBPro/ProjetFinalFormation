package fr.projetFormation.Hashi.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import fr.projetFormation.Hashi.services.CuisinierService;
import fr.projetFormation.Hashi.services.auth.CustomUserDetails;

@RestController
@RequestMapping("/api/cuisinier")
@CrossOrigin(origins = "*")
public class CuisinierRestController {

	@Autowired
	private CuisinierService cuisinierService;

	@GetMapping("/{id}")
	@JsonView(JsonViews.PersonneWithUser.class)
	public Cuisinier byId(@PathVariable("id") Long id) {
		return cuisinierService.byId(id);
	}

	@GetMapping("/local")
	@JsonView(JsonViews.PersonneWithUser.class)
	public Cuisinier byId(@AuthenticationPrincipal CustomUserDetails cUD) {
		return cuisinierService.byId(cUD.getUser().getPersonne().getId());
	}

	@GetMapping("/available")
	@JsonView(JsonViews.Common.class)
	public List<Cuisinier> allAvailable(){
		return cuisinierService.allAvailable();
	}

	@PostMapping("")
	@JsonView(JsonViews.Common.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cuisinier create(@Valid @RequestBody Cuisinier cuisinier, BindingResult br) {
		return cuisinierService.create(cuisinier);
	}

	@PutMapping("/local")
	@JsonView(JsonViews.Common.class)
	public Cuisinier update(@Valid @RequestBody Cuisinier cuisinier, BindingResult br, @AuthenticationPrincipal CustomUserDetails cUD) {
		boolean changePassword = false;
		Cuisinier cuisinierEnBase = cuisinierService.byId(cUD.getUser().getPersonne().getId());
		cuisinierEnBase.setNom(cuisinier.getNom());
		cuisinierEnBase.setPrenom(cuisinier.getPrenom());
        cuisinierEnBase.setEmail(cuisinier.getEmail());
        cuisinierEnBase.getUser().setLogin(cuisinier.getUser().getLogin());
		// Si on a un nouveau mdp
		if (!cuisinier.getUser().getPassword().equals("")) {
			cuisinierEnBase.getUser().setPassword(cuisinier.getUser().getPassword());
			changePassword = true;
		}
		return cuisinierService.update(cuisinierEnBase, changePassword);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		cuisinierService.delete(id);
	}

	@DeleteMapping("/local")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@AuthenticationPrincipal CustomUserDetails cUD) {
		cuisinierService.delete(cUD.getUser().getPersonne().getId());
	}
}
