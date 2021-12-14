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
import org.springframework.security.crypto.password.PasswordEncoder;
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
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Client create(Client client) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Client>> violations = validator.validate(client);
		if (violations.isEmpty()) {
			User user = client.getUser();
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoles(Arrays.asList(Role.ROLE_CLIENT));
			user.setEnable(true);
			userRepository.save(user);
			client = clientRepository.save(client);
			return client;
		} else {
			throw new ClientException();
		}
	}

	public Client update(Client client, Boolean changePassword) {
		User user = client.getUser();
		User userEnBase = userRepository.findByLogin(user.getLogin()).orElseThrow(ClientException::new);
		if (changePassword){
			userEnBase.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		userRepository.save(userEnBase);
		return clientRepository.save(client);
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

	// not necessary because of users
	// public Client byLoginOrEmail(String login) {
	// return
	// clientRepository.findByLoginOrEmail(login).orElseThrow(ClientException::new);
	// }
}
