package testService;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projetFinal.config.AppConfig;
import projetFinal.entity.Adresse;
import projetFinal.entity.Cuisinier;
import projetFinal.entity.Gerant;
import projetFinal.entity.LigneCarte;
import projetFinal.entity.LigneCartePk;
import projetFinal.entity.Plat;
import projetFinal.entity.Restaurant;
import projetFinal.services.CuisinierService;
import projetFinal.services.GerantService;
import projetFinal.services.PlatService;
import projetFinal.services.RestaurantService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
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

    // @BeforeClass
    // public static void startTest(){
    // }

    @Test
    public void remplissageBdd() {

        Cuisinier cuistot1 = new Cuisinier();
        cuistot1.setNom("Baron");
        cuistot1.setPrenom("Louis");
        cuistot1.setLogin("barlou");
        cuistot1.setPassword("motDePasse");
        cuistot1.setEmail("louis@hotmail.com");

        Gerant gerant1 = new Gerant();
        gerant1.setNom("Henan");
        gerant1.setPrenom("Martial");
        gerant1.setLogin("pecaplop");
        gerant1.setPassword("motDePasse");
        gerant1.setEmail("martial@hotmail.com");
        gerantService.save(gerant1);

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
        Adresse adresse1 = new Adresse(10, "Rue Saint Anne", "35000", "Rennes");
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
        cuisinierService.save(cuistot1);

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