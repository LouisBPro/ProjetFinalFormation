package testService;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import projetFinal.config.AppConfig;
import projetFinal.entity.Plat;
import projetFinal.exceptions.PlatException;
import projetFinal.services.PlatService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class PlatServiceTest {
	@Autowired
	private PlatService platService;
	
	private Plat getPlat() {
		Plat plat = new Plat("nom test", "description du plat test");
		return plat;
	}

	@Test(expected = PlatException.class)
	public void testSave() {
		Plat produit = getPlat();
		platService.save(produit);
		assertNotNull(platService.byId(produit.getId()));
		platService.delete(produit);
		platService.byId(produit.getId());
	}

}
