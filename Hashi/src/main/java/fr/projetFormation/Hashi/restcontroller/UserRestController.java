package fr.projetFormation.Hashi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.projetFormation.Hashi.repositories.UserRepository;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins="*")
public class UserRestController {
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/{login}")
	public boolean isUsed(@PathVariable("login") String login) {
		return userRepo.findByLogin(login).isPresent();
	}
}
