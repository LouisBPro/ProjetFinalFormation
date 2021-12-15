package fr.projetFormation.Hashi.restcontroller;

import java.util.List;
import java.util.Set;

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

import fr.projetFormation.Hashi.entities.Gerant;
import fr.projetFormation.Hashi.entities.JsonViews;
import fr.projetFormation.Hashi.entities.Restaurant;
import fr.projetFormation.Hashi.services.GerantService;
import fr.projetFormation.Hashi.services.RestaurantService;
import fr.projetFormation.Hashi.services.auth.CustomUserDetails;

@RestController
@RequestMapping("/api/gerant")
@CrossOrigin(origins = "*")
public class GerantRestController {

    @Autowired
    private GerantService gerantService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/{id}")
    @JsonView(JsonViews.PersonneWithUser.class)
    public Gerant byId(@PathVariable("id") Long id, @AuthenticationPrincipal CustomUserDetails cUD) {
        System.out.println(cUD.getUser().getLogin());
        return gerantService.byId(id);
    }

    @GetMapping("/local")
    @JsonView(JsonViews.GerantAvecRestaurantsEtUser.class)
    public Gerant local(@AuthenticationPrincipal CustomUserDetails cUD) {
        return gerantService.byId(cUD.getUser().getPersonne().getId());
    }

    @PostMapping("")
    @JsonView(JsonViews.Common.class)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Gerant create(@Valid @RequestBody Gerant gerant, BindingResult br) {
        return gerantService.create(gerant);
    }

    @PutMapping("/local")
    @JsonView(JsonViews.Common.class)
    public Gerant update(@Valid @RequestBody Gerant gerant, BindingResult br, @AuthenticationPrincipal CustomUserDetails cUD) {
    	boolean changePassword = false;
        Gerant gerantEnBase = gerantService.byId(cUD.getUser().getPersonne().getId());
        gerantEnBase.setNom(gerant.getNom());
        gerantEnBase.setPrenom(gerant.getPrenom());
        gerantEnBase.setEmail(gerant.getEmail());
        gerantEnBase.getUser().setLogin(gerant.getUser().getLogin());
		// Si on a un nouveau mdp
		if (!gerant.getUser().getPassword().equals("")) {
			gerantEnBase.getUser().setPassword(gerant.getUser().getPassword());
			changePassword = true;
		}
        return gerantService.update(gerantEnBase, changePassword);
    }

    // @PutMapping("/restaurants/manages")
    // @JsonView(JsonViews.Common.class)
    // public Gerant updateRestaurantsManages(@Valid @RequestBody Gerant gerant, BindingResult br, @AuthenticationPrincipal CustomUserDetails cUD) {
    //     Gerant gerantEnBase = gerantService.byId(cUD.getUser().getPersonne().getId());
    //     gerantEnBase.setRestaurants(gerant.getRestaurants());
    //     return gerantService.update(gerantEnBase);
    // }

    @PutMapping("/restaurants/manages/{id}")
    @JsonView(JsonViews.Common.class)
    public Gerant updateRestaurantsManages(@PathVariable("id") Long id, @AuthenticationPrincipal CustomUserDetails cUD){
        Gerant gerantEnBase = gerantService.byId(cUD.getUser().getPersonne().getId());
        Set<Restaurant> restaurantsManages = gerantEnBase.getRestaurants();
        Restaurant nouveauRestaurant = restaurantService.byId(id);
        if (nouveauRestaurant.getGerant() == null){
            nouveauRestaurant.setGerant(gerantEnBase);
            restaurantsManages.add(nouveauRestaurant);
            gerantEnBase.setRestaurants(restaurantsManages);
        }
        return gerantService.update(gerantEnBase, false);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        gerantService.delete(id);
    }

    @DeleteMapping("/local")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal CustomUserDetails cUD) {
        gerantService.delete(cUD.getUser().getPersonne().getId());
    }
}
