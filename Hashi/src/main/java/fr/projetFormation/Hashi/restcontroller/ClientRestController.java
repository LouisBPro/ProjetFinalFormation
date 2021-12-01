package fr.projetFormation.Hashi.restcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
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
import fr.projetFormation.Hashi.entities.User;
import fr.projetFormation.Hashi.services.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientRestController {
	
	@Autowired
	private ClientService clientService;


	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Client byId(@PathVariable("id") Long id) {
		return clientService.byId(id);
	}

	@PostMapping("")
	@JsonView(JsonViews.Common.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Client create(@Valid @RequestBody Client client, BindingResult br) {
		return clientService.save(client);
	}
	
	@PostMapping("")
	@JsonView(JsonViews.Common.class)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Client create(@Valid @RequestBody Client client,@Valid @RequestBody User user, BindingResult br) {
		return clientService.save(client);
	}

	@PutMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Client update(@Valid @RequestBody Client client, BindingResult br, @PathVariable("id") Long id) {
		Client clientEnBase = clientService.byId(id);
		clientEnBase.setNom(client.getNom());
		clientEnBase.setPrenom(client.getPrenom());
		return clientService.save(clientEnBase);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		clientService.delete(id);
	}

}