package fr.projetFormation.Hashi.restcontroller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.projetFormation.Hashi.entities.JsonViews;
import fr.projetFormation.Hashi.entities.User;
import fr.projetFormation.Hashi.services.auth.CustomUserDetails;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthRestController {

	@GetMapping("")
	@JsonView(JsonViews.UserWithPersonne.class)
	public User login(@AuthenticationPrincipal CustomUserDetails cUD){
		return cUD.getUser();
	}
}
