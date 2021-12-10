package fr.projetFormation.Hashi.restcontroller;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;

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

import fr.projetFormation.Hashi.entities.JsonViews;
import fr.projetFormation.Hashi.entities.Restaurant;
import fr.projetFormation.Hashi.services.PlatService;
import fr.projetFormation.Hashi.services.RestaurantService;

@RestController
@RequestMapping("/api/restaurant")
@CrossOrigin(origins = "*")
public class RestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private PlatService platService;

    @GetMapping("/{id}")
    @JsonView(JsonViews.RestaurantAvecTout.class)
    public Restaurant byId(@PathVariable("id") Long id) {
        return restaurantService.byId(id);
    }

    @GetMapping("/location/{location}")
    @JsonView(JsonViews.Common.class)
    public List<Restaurant> byVilleOrCodePostal(@PathVariable("location") String location) {
        return restaurantService.byVilleOrCodePostal(location);
    }

    @PostMapping("")
    @JsonView(JsonViews.RestaurantAvecEmployes.class)
    public Restaurant create(@Valid @RequestBody Restaurant restaurant, BindingResult br) {
        restaurant.getCuisiniers().forEach(c->{
            c.setRestaurant(restaurant);
        });
        return restaurantService.save(restaurant);
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