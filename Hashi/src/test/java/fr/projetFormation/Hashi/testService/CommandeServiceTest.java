package testService;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projetFinal.config.AppConfig;
import projetFinal.entity.Adresse;
import projetFinal.entity.Client;
import projetFinal.entity.Commande;
import projetFinal.entity.LigneCommande;
import projetFinal.entity.LigneCommandePK;
import projetFinal.entity.Plat;
import projetFinal.entity.Restaurant;
import projetFinal.entity.Statut;
import projetFinal.exceptions.CommandeException;
import projetFinal.services.ClientService;
import projetFinal.services.CommandeService;
import projetFinal.services.PlatService;
import projetFinal.services.RestaurantService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class CommandeServiceTest {

	@Autowired
	private CommandeService commandeService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private PlatService platService;

	@Autowired
	private RestaurantService restaurantService;

//	@Test
	public void testSave() {
		Client cl = new Client();
		cl.setEmail("jjj@kkk.com");
		cl.setLogin("jjj");
		cl.setNom("jkj");
		cl.setAdresse(new Adresse(1, "rue", "35200", "Rennes"));
		cl.setPrenom("grdsf");
		cl.setPassword("jjj");
		clientService.save(cl);

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
		restaurantService.save(resto1);

		Commande c = new Commande();
		c.setClient(cl);
		c.setStatut(Statut.Validated);
		c.setRestaurant(resto1);

		LigneCommande lc1 = new LigneCommande(new LigneCommandePK(c, plat1), 2);

		LigneCommande lc2 = new LigneCommande(new LigneCommandePK(c, plat2), 1);
		Set<LigneCommande> lc = new HashSet<LigneCommande>();
		lc.add(lc1);
		lc.add(lc2);

		c.setLignesCommande(lc);
		commandeService.save(c);
		assertNotNull(commandeService.byRestaurant(resto1));

	}

//	@Test
	public void testByClientWithLigneCommande() {
		Client cl = new Client();
		cl.setEmail("jjj@kkk.com");
		cl.setLogin("jjj");
		cl.setNom("jkj");
		cl.setAdresse(new Adresse(1, "rue", "35200", "Rennes"));
		cl.setPrenom("grdsf");
		cl.setPassword("jjj");
		clientService.save(cl);
		List<Commande> c = commandeService.byClientWithLigneCommande(cl);
		assertNotNull(c);
	}

//	@Test
	public void testByRestaurant() {
		Restaurant resto1 = new Restaurant();
		Adresse adresse1 = new Adresse(10, "Rue Saint Anne", "35000", "Rennes");
		resto1.setNom("Restaurant - Saint Anne");
		resto1.setAdresse(adresse1);
		restaurantService.save(resto1);
		assertNotNull(commandeService.byRestaurant(resto1));
	}
	
//	@Test
	public void testByRestaurantAndStatut() {
		Restaurant resto1 = new Restaurant();
		Adresse adresse1 = new Adresse(10, "Rue Saint Anne", "35000", "Rennes");
		resto1.setNom("Restaurant - Saint Anne");
		resto1.setAdresse(adresse1);
		restaurantService.save(resto1);
		assertNotNull(commandeService.byRestaurantAndStatut(resto1, Statut.Validated));
		
	}
	
//	@Test
	public void testByIdWithLigneCommande() {
		Commande c = commandeService.byIdWithLigneCommande(1L);
		assertNotNull(c);
	}
	
	// @Test
	public void testRemoveOneLigneCommande() {
		Plat p = platService.byId(2L);
		Commande c = commandeService.byIdWithLigneCommande(1L);
		commandeService.removeOneLigneCommande(c, p);
		assertNotNull(commandeService.byIdWithLigneCommande(1L));
	}

	@Test(expected = CommandeException.class)
	public void testDelete() {
		Commande c = commandeService.byId(1L);
		commandeService.delete(c);
		commandeService.byId(1L);
	}

	// 

}
