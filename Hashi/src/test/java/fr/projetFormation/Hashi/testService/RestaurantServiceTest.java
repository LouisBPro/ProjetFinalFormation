package fr.projetFormation.Hashi.testService;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import fr.projetFormation.Hashi.entities.Adresse;
import fr.projetFormation.Hashi.entities.Client;
import fr.projetFormation.Hashi.entities.Cuisinier;
import fr.projetFormation.Hashi.entities.Gerant;
import fr.projetFormation.Hashi.entities.LigneCarte;
import fr.projetFormation.Hashi.entities.LigneCartePk;
import fr.projetFormation.Hashi.entities.Plat;
import fr.projetFormation.Hashi.entities.Restaurant;
import fr.projetFormation.Hashi.entities.User;
import fr.projetFormation.Hashi.services.ClientService;
import fr.projetFormation.Hashi.services.CuisinierService;
import fr.projetFormation.Hashi.services.GerantService;
import fr.projetFormation.Hashi.services.PlatService;
import fr.projetFormation.Hashi.services.RestaurantService;

@SpringBootTest
@Rollback
public class RestaurantServiceTest {

    @Autowired
    RestaurantService restaurantService;
    @Autowired
    PlatService platService;
    @Autowired
    CuisinierService cuisinierService;
    @Autowired
    GerantService gerantService;
    @Autowired
    ClientService clientService;

    // @BeforeClass
    // public static void startTest(){
    // }

    @Test
    public void remplissageBdd() {

        User userClient = new User();
        userClient.setLogin("client");
        userClient.setPassword("client");

        Client client = new Client();
        client.setAdresse(new Adresse("5","Square marcel bozzuffi", "35000", "Rennes"));
        client.setEmail("client@gmail.com");
        client.setNom("Client");
        client.setPrenom("Client");
        client.setUser(userClient);

        clientService.create(client);

        User userCuisinier = new User();
        userCuisinier.setLogin("cuisinier");
        userCuisinier.setPassword("cuisinier");

        User userGerant = new User();
        userGerant.setLogin("gerant");
        userGerant.setPassword("gerant");

        Cuisinier cuistot1 = new Cuisinier();
        cuistot1.setNom("Baron");
        cuistot1.setPrenom("Louis");
        cuistot1.setEmail("louis@hotmail.com");
        cuistot1.setUser(userCuisinier);

        Gerant gerant1 = new Gerant();
        gerant1.setNom("Henan");
        gerant1.setPrenom("Martial"); 
        gerant1.setEmail("martial@hotmail.com");
        gerant1.setUser(userGerant);
        gerantService.create(gerant1);

        Plat plat1 = new Plat();
        plat1.setDescription("C'est un super bon plat !");
        plat1.setNom("Tofu fumé au gingembre");
        plat1.setPrix(10.50f);
        platService.save(plat1);

        Plat plat2 = new Plat();
        plat2.setDescription("C'est un super mauvais plat !");
        plat2.setNom("Poulet confit au sang de caille ");
        plat2.setPrix(18.50f);
        platService.save(plat2);

        Restaurant resto1 = new Restaurant();
        Adresse adresse1 = new Adresse("10", "Rue Saint Anne", "35000", "Rennes");
        resto1.setNom("Restaurant - Saint Anne");
        resto1.setAdresse(adresse1);

        LigneCarte ligneCarte1 = new LigneCarte();
        LigneCartePk pk1 = new LigneCartePk(plat1, resto1);
        ligneCarte1.setId(pk1);
        ligneCarte1.setDisponibilite(true);

        LigneCarte ligneCarte2 = new LigneCarte();
        LigneCartePk pk2 = new LigneCartePk(plat2, resto1);
        ligneCarte2.setId(pk2);
        ligneCarte2.setDisponibilite(true);

        Set<LigneCarte> setLignesCarte = new HashSet<LigneCarte>();
        System.out.println(setLignesCarte.add(ligneCarte1));
        System.out.println(setLignesCarte.add(ligneCarte2));
        resto1.setLignesCarte(setLignesCarte);

        resto1.setGerant(gerant1);
        Set<Cuisinier> cuisinierSet = new HashSet<Cuisinier>();
        cuisinierSet.add(cuistot1);

        cuistot1.setRestaurant(resto1);

        restaurantService.save(resto1);
        cuisinierService.create(cuistot1);

    }

    @Test
    public void testById() {
        Restaurant resto = restaurantService.byId(1L);
        System.out.println(resto);
        assertNotNull(resto);
    }

    @Test
    public void testByIdWithLigneCarte() {
        Restaurant resto = restaurantService.byIdWithLigneCarte(1L);
        System.out.println(resto);
        for (LigneCarte lc : resto.getLignesCarte()) {
            System.out.println(lc);
        }
        assertFalse(resto.getLignesCarte().isEmpty());
    }

    @Test
    public void testDelete() {
        Restaurant resto = restaurantService.byId(1L);
        assertNotNull(resto);
        assertTrue(gerantService.byIdWithRestaurants(200L).getRestaurants().contains(resto));
        assertTrue(cuisinierService.byId(201L).getRestaurant().equals(resto));
        restaurantService.delete(resto);
        assertFalse(gerantService.byIdWithRestaurants(200L).getRestaurants().contains(resto));
        assertNull(cuisinierService.byId(201L).getRestaurant());
    }

    @Test
    public void testRemoveOneLigneCarte() {
        Restaurant resto = restaurantService.byIdWithLigneCarte(1L);
        assertFalse(resto.getLignesCarte().isEmpty());
        List<Plat> plats = new ArrayList<Plat>();
        int i = 0;
        for (LigneCarte lc : resto.getLignesCarte()) {
            plats.add(lc.getId().getPlat());
            System.out.println("Plat" + i + ": " + plats.get(i));
            restaurantService.removeOneLigneCarte(resto, plats.get(i));
            i++;
        }
        assertTrue(restaurantService.byIdWithLigneCarte(1L).getLignesCarte().isEmpty());
    }

    @Test
    public void testByNom() {
        List<Restaurant> restos = restaurantService.byNom("Restaurant - Saint Anne");
        assertFalse(restos.isEmpty());
        restos = restaurantService.byNom("Restaurant");
        assertFalse(restos.isEmpty());
        restos = restaurantService.byNom("Resturant");
        assertTrue(restos.isEmpty());
    }

    @Test
    public void testByVilleOrCodePostal() {
        List<Restaurant> restos = restaurantService.byVilleOrCodePostal("35");
        assertFalse(restos.isEmpty());
        restos = restaurantService.byVilleOrCodePostal("35000");
        assertFalse(restos.isEmpty());
        restos = restaurantService.byVilleOrCodePostal("Rennes");
        assertFalse(restos.isEmpty());
        restos = restaurantService.byVilleOrCodePostal("Re");
        assertFalse(restos.isEmpty());
        restos = restaurantService.byVilleOrCodePostal("36000");
        assertTrue(restos.isEmpty());
        restos = restaurantService.byVilleOrCodePostal("Rqnnes");
        assertTrue(restos.isEmpty());
    }

    @Test
    public void testAll() {
    }
}
