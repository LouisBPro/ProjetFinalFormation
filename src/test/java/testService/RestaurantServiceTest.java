package testService;

import static org.junit.Assert.*;

import java.util.HashSet;
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
//@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Rollback
public class RestaurantServiceTest {

    @Autowired
    static RestaurantService restaurantService;
    @Autowired
    static PlatService platService;
    @Autowired
    static CuisinierService cuisinierService;
    @Autowired 
    static GerantService gerantService;

    @BeforeClass
    public static void startTest(){
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

        Plat plat1 = new Plat();
        plat1.setDescription("C'est un super bon plat !");
        plat1.setNom("Tofu fumé au gingembre");
        plat1.setPrix(10.50f);
        
        Plat plat2 = new Plat();
        plat2.setDescription("C'est un super mauvais plat !");
        plat2.setNom("Poulet confit au sang de caille ");
        plat2.setPrix(18.50f);
        
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
        setLignesCarte.add(ligneCarte1);
        setLignesCarte.add(ligneCarte2);
        resto1.setLignesCarte(setLignesCarte);

        resto1.setGerant(gerant1);
        Set<Cuisinier> cuisinierSet = new HashSet<Cuisinier>();
        cuisinierSet.add(cuistot1);
        resto1.setCuisiniers(cuisinierSet);

        cuistot1.setRestaurant(resto1);
        gerant1.addRestaurant(resto1);

        cuisinierService.save(cuistot1);
        gerantService.save(gerant1);
        platService.save(plat1);
        platService.save(plat2);
        restaurantService.save(resto1);
    }

	@Test
	public void testInsert() {
        List<Restaurant> resto = restaurantService.byVille("Rennes");
        System.out.println(resto);
        assertNotNull(resto);
	}

}
