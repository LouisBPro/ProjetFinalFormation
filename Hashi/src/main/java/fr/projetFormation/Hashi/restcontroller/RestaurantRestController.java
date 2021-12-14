package fr.projetFormation.Hashi.restcontroller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;

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

import fr.projetFormation.Hashi.entities.Cuisinier;
import fr.projetFormation.Hashi.entities.Gerant;
import fr.projetFormation.Hashi.entities.JsonViews;
import fr.projetFormation.Hashi.entities.LigneCarte;
import fr.projetFormation.Hashi.entities.Restaurant;
import fr.projetFormation.Hashi.services.CuisinierService;
import fr.projetFormation.Hashi.services.GerantService;
import fr.projetFormation.Hashi.services.PlatService;
import fr.projetFormation.Hashi.services.RestaurantService;
import fr.projetFormation.Hashi.services.auth.CustomUserDetails;

@RestController
@RequestMapping("/api/restaurant")
@CrossOrigin(origins = "*")
public class RestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private PlatService platService;
    @Autowired
    private GerantService gerantService;
    @Autowired
    private CuisinierService cuisinierService;

    @GetMapping("/{id}")
    @JsonView(JsonViews.RestaurantAvecLignesCarte.class)
    public Restaurant byId(@PathVariable("id") Long id) {
        return restaurantService.byId(id);
    }

    @GetMapping("/location/{location}")
    @JsonView(JsonViews.Common.class)
    public List<Restaurant> byVilleOrCodePostal(@PathVariable("location") String location) {
        return restaurantService.byVilleOrCodePostal(location);
    }
    @GetMapping("")
    @JsonView(JsonViews.Common.class)
    public List<Restaurant> all() {
        return restaurantService.all();
    }

    @GetMapping("/available")
	@JsonView(JsonViews.Common.class)
	public List<Restaurant> allAvailable(){
		return restaurantService.allAvailable();
	}

    @PostMapping("")
    @JsonView(JsonViews.RestaurantAvecEmployes.class)
    public Restaurant create(@AuthenticationPrincipal CustomUserDetails cUD, @Valid @RequestBody Restaurant restaurant, BindingResult br) {
        Gerant gerantConnecte = gerantService.byId(cUD.getUser().getPersonne().getId());
        restaurant.setGerant(gerantConnecte);
        Restaurant restau = restaurantService.save(restaurant);
        restaurant.getCuisiniers().forEach(c->{
            Cuisinier cuisinierEnBase = cuisinierService.byId(c.getId());
            cuisinierEnBase.setRestaurant(restaurant);
            cuisinierService.update(cuisinierEnBase);
        });
        return restau;
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.RestaurantAvecTout.class)
    public Restaurant update(@Valid @RequestBody Restaurant restaurant, BindingResult br, @PathVariable("id") Long id) {
        Restaurant restaurantEnBase = restaurantService.byId(id);
        restaurantEnBase.setAdresse(restaurant.getAdresse());
        restaurantEnBase.setCuisiniers(restaurant.getCuisiniers());
        restaurantEnBase.setGerant(restaurant.getGerant());
        restaurantEnBase.setLignesCarte(restaurant.getLignesCarte());
        restaurantEnBase.setNom(restaurant.getNom());
        return restaurantService.save(restaurantEnBase);
    }
    @GetMapping("/ligneCarte/{restaurantId}")
    @JsonView(JsonViews.RestaurantAvecLignesCarte.class)
    public Set<LigneCarte> byIdWithLigneCarte(@PathVariable("restaurantId") Long restaurantId) {
		return restaurantService.byIdWithLigneCarte(restaurantId).getLignesCarte();
	}

    @DeleteMapping("/ligneCarte/{restaurantId}/{platId}")
    @JsonView(JsonViews.RestaurantAvecLignesCarte.class)
    public Restaurant removeOneLigneCarte(@PathVariable("restaurantId") Long restaurantId, @PathVariable("platId") Long platId) {
        return restaurantService.removeOneLigneCarte(restaurantService.byId(restaurantId), platService.byId(platId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        restaurantService.delete(restaurantService.byId(id));
    }

}