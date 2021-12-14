package fr.projetFormation.Hashi.restcontroller;

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

import fr.projetFormation.Hashi.entities.Client;
import fr.projetFormation.Hashi.entities.JsonViews;
import fr.projetFormation.Hashi.exceptions.ClientException;
import fr.projetFormation.Hashi.services.ClientService;
import fr.projetFormation.Hashi.services.auth.CustomUserDetails;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "*")
public class ClientRestController {

	@Autowired
	private ClientService clientService;

	@GetMapping("/{id}")
	@JsonView(JsonViews.PersonneWithUser.class)
	public Client byId(@PathVariable("id") Long id, @AuthenticationPrincipal CustomUserDetails cUD) {
		return clientService.byId(id);
	}

	@GetMapping("/local")
	@JsonView(JsonViews.PersonneWithUser.class)
	public Client local(@AuthenticationPrincipal CustomUserDetails cUD) {
		Client client = clientService.byId(cUD.getUser().getPersonne().getId());
		return client;
	}

	@PostMapping("")
	@JsonView(JsonViews.Common.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Client create(@Valid @RequestBody Client client, BindingResult br) {
		return clientService.create(client);
	}

	@PutMapping("/local")
	@JsonView(JsonViews.Common.class)
	public Client update(@Valid @RequestBody Client client, BindingResult br, @AuthenticationPrincipal CustomUserDetails cUD) {
		boolean changePassword = false;
		Client clientEnBase = clientService.byId(cUD.getUser().getPersonne().getId());
		clientEnBase.setNom(client.getNom());
		clientEnBase.setPrenom(client.getPrenom());
		clientEnBase.setEmail(client.getEmail());
		clientEnBase.getUser().setLogin(client.getUser().getLogin());
		// Si on a un nouveau mdp
		if (!client.getUser().getPassword().equals("")) {
			clientEnBase.getUser().setPassword(client.getUser().getPassword());
			changePassword = true;
		}
		// Sinon on garde l'ancien donc on y touche pas
		clientEnBase.getAdresse().setNumero(client.getAdresse().getNumero());
		clientEnBase.getAdresse().setRue(client.getAdresse().getRue());
		clientEnBase.getAdresse().setCodePostal(client.getAdresse().getCodePostal());
		clientEnBase.getAdresse().setVille(client.getAdresse().getVille());
		return clientService.update(clientEnBase, changePassword);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		clientService.delete(id);
	}

	@DeleteMapping("/local")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id, @AuthenticationPrincipal CustomUserDetails cUD) {
		clientService.delete(cUD.getUser().getPersonne().getId());
	}

}
