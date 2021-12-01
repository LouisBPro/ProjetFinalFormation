package fr.projetFormation.Hashi.restcontroller;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.projetFormation.Hashi.entities.Commande;
import fr.projetFormation.Hashi.entities.JsonViews;
import fr.projetFormation.Hashi.entities.Restaurant;
import fr.projetFormation.Hashi.entities.Statut;
import fr.projetFormation.Hashi.services.CommandeService;
import fr.projetFormation.Hashi.services.RestaurantService;

@RestController
@RequestMapping("/api/commande")
public class CommandeRestController {
    
    @Autowired
    private CommandeService commandeService;
    
    @Autowired
    private RestaurantService restaurantService;

    //avec lignes commande et client restaurant
    @GetMapping("/{id}")
    @JsonView(JsonViews.Common.class)
    public Commande byId(@PathVariable("id") Long id){
        return commandeService.byId(id);
    }

    @GetMapping("/{restaurantId}&{statut}")
    @JsonView(JsonViews.Common.class)
    public List<Commande> byRestaurantIdAndStatut(@PathVariable("restaurantId") Long restaurantId, @PathVariable("statut") Statut statut){
        return commandeService.byRestaurantAndStatut(restaurantService.byId(restaurantId), statut);
    }

    // EST CE QUE CEST INTELLIGENT DE FAIRE LE BY RESTAURANT ICI? SI OUI COMMENT FAIRE
    // COMMENT GERER LE STATUT DANS LA REQUETE GET?

    // @GetMapping("/{restaurantId}")
    // @JsonView(JsonViews.Common.class)
    // public List<Commande> byRestaurantId(@PathVariable("restaurantId") Long restaurantId){
    //     return commandeService.byRestaurant(restaurantService.byId(restaurantId));
    // }

    // with ligne commande and client
    public List<Commande> byClient(){
        return null;
    }
    
    public Commande create(){
        return null;
    }

    public Commande update(){
        return null;
    }

    // TODO ? removeOneLigneCommande(commande, plat)

    public void delete(){
    }


}
