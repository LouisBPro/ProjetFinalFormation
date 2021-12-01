package fr.projetFormation.Hashi.services;

import java.util.Arrays;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.projetFormation.Hashi.entities.Client;
import fr.projetFormation.Hashi.entities.Role;
import fr.projetFormation.Hashi.entities.User;
import fr.projetFormation.Hashi.exceptions.ClientException;
import fr.projetFormation.Hashi.repositories.ClientRepository;
import fr.projetFormation.Hashi.repositories.CommandeRepository;
import fr.projetFormation.Hashi.repositories.UserRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CommandeRepository commandeRepository;
	@Autowired
	private UserRepository userRepository;

	public Client save(Client client) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Client>> violations = validator.validate(client);
		if (violations.isEmpty()) {
			User user = client.getUser();
			user.setPassword(user.getPassword());
			user.setRoles(Arrays.asList(Role.ROLE_CLIENT));
			userRepository.save(user);
			client.setUser(user);
			return clientRepository.save(client);
		} else {
			throw new ClientException();
		}
	}

	public void delete(Client client) {
		Client clientEnBase = clientRepository.findById(client.getId()).orElseThrow(ClientException::new);
		commandeRepository.removeClientFromCommandeByClient(clientEnBase);
		clientRepository.delete(clientEnBase);
		userRepository.delete(clientEnBase.getUser());
	}
	public void delete(Long id) {
		delete(clientRepository.findById(id).orElseThrow(ClientException::new));
	}

	public Page<Client> clientFirstPage(int size) {
		Pageable pageable = PageRequest.of(0, size);
		return clientRepository.findAll(pageable);
	}

	public Page<Client> clientNextPage(Page<Client> page) {
		return clientRepository.findAll(page.nextOrLastPageable());
	}

	public Page<Client> clientPreviousPage(Page<Client> page) {
		return clientRepository.findAll(page.previousOrFirstPageable());
	}

	public Client byId(Long id) {
		return clientRepository.findByIdWithCommandes(id).orElseThrow(ClientException::new);
	}
	
	
	//not necessary because of users
//	public Client byLoginOrEmail(String login) {
//		return clientRepository.findByLoginOrEmail(login).orElseThrow(ClientException::new);
//	}
}
