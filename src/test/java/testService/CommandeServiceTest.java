package testService;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projetFinal.entity.Commande;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CreationBddTest.class })
public class CommandeServiceTest {

	@Test
	public void testSave() {
		Commande c = new Commande();
		
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveOneLigneCommande() {
		fail("Not yet implemented");
	}

	@Test
	public void testByClientWithLigneCommande() {
		fail("Not yet implemented");
	}

	@Test
	public void testByRestaurantAndStatut() {
		fail("Not yet implemented");
	}

	@Test
	public void testByRestaurant() {
		fail("Not yet implemented");
	}

	@Test
	public void testByIdWithLigneCommande() {
		fail("Not yet implemented");
	}

}
