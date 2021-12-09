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

import fr.projetFormation.Hashi.entities.Commande;
import fr.projetFormation.Hashi.entities.JsonViews;
import fr.projetFormation.Hashi.entities.Statut;
import fr.projetFormation.Hashi.services.ClientService;
import fr.projetFormation.Hashi.services.CommandeService;
import fr.projetFormation.Hashi.services.RestaurantService;

@RestController
@RequestMapping("/api/commande")
@CrossOrigin(origins="*")
public class CommandeRestController {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ClientService clientService;

    // avec lignes commande et client restaurant
    @GetMapping("/{id}")
    @JsonView(JsonViews.Common.class)
    public Commande byId(@PathVariable("id") Long id) {
        return commandeService.byId(id);
    }

    @GetMapping("/restaurant/{restaurantId}/{statut}")
    @JsonView(JsonViews.Common.class)
    public List<Commande> byRestaurantIdAndStatut(@PathVariable("restaurantId") Long restaurantId,
            @PathVariable("statut") String statut) {
        Statut s;
        switch (statut) {
            case "validated":
                s = Statut.Validated;
                break;
            case "waiting":
                s = Statut.Waiting;
                break;
            case "delivered":
                s = Statut.Delivered;
                break;
            case "preparation":
                s = Statut.Preparation;
                break;
            case "done":
                s = Statut.Done;
                break;
            case "canceled":
                s = Statut.Cancelled;
                break;
            default:
                s = Statut.None;
                break;
        }
        return commandeService.byRestaurantAndStatut(restaurantService.byId(restaurantId), s);
    }

    @GetMapping("/restaurant/{restaurantId}")
    @JsonView(JsonViews.Common.class)
    public List<Commande> byRestaurantId(@PathVariable("restaurantId") Long restaurantId) {
        return commandeService.byRestaurant(restaurantService.byId(restaurantId));
    }

    @GetMapping("/client/{clientId}")
    @JsonView(JsonViews.Common.class)
    public List<Commande> byClientId(@PathVariable("clientId") Long clientId) {
        return commandeService.byClientWithLigneCommande(clientService.byId(clientId));
    }

    @PostMapping("")
    @JsonView(JsonViews.Common.class)
    public Commande create(@Valid @RequestBody Commande commande, BindingResult br) {
        commande.getLignesCommande().forEach(lc -> {
            lc.getId().setCommande(commande);
        });
        return commandeService.save(commande);
    }

    @PutMapping("/{id}/{statut}")
    @JsonView(JsonViews.Common.class)
    public Commande updateCommandeStatut(@PathVariable("id") Long id, @PathVariable("statut") String statut) {
        Commande c = commandeService.byId(id);
        Statut s;
        switch (statut) {
            case "validated":
                s = Statut.Validated;
                break;
            case "waiting":
                s = Statut.Waiting;
                break;
            case "delivered":
                s = Statut.Delivered;
                break;
            case "preparation":
                s = Statut.Preparation;
                break;
            case "done":
                s = Statut.Done;
                break;
            case "canceled":
                s = Statut.Cancelled;
                break;
            default:
                s = Statut.None;
                break;
        }
        c.setStatut(s);
        return commandeService.save(c);
    }

    // TODO ? removeOneLigneCommande(commande, plat)

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        commandeService.delete(commandeService.byId(id));
    }

}
