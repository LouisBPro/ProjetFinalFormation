package fr.projetFormation.Hashi.testService;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.projetFormation.Hashi.entities.Plat;
import fr.projetFormation.Hashi.services.PlatService;

@SpringBootTest
public class PlatServiceTest {
	@Autowired
	private PlatService platService;

	private Plat getPlat() {
		Plat plat = new Plat("nom test", "description du plat test");
		return plat;
	}

	// @Test(expected = PlatException.class)
	// public void testSave() {
	// 	Plat produit = getPlat();
	// 	platService.save(produit);
	// 	assertNotNull(platService.byId(produit.getId()));
	// 	platService.delete(produit);
	// 	platService.byId(produit.getId());
	// }

	@Test
	public void testall() {
		assertNotNull(platService.all());
	}
	
	@Test
	public void testByNom() {
		assertNotNull(platService.byNom("nom test"));
		assertNotNull(platService.byNom("nom "));
	}	

}
