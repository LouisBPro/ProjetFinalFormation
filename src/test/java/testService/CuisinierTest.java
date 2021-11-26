package testService;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projetFinal.config.AppConfig;
import projetFinal.entity.Cuisinier;

import projetFinal.services.CuisinierService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class CuisinierTest {
	 @Autowired
	    CuisinierService cuisinierService;
	 
	 private Cuisinier getcuisot() {
		 Cuisinier cuistot1 = new Cuisinier();
	        cuistot1.setNom("Baron");
	        cuistot1.setPrenom("Louis");
	        cuistot1.setPassword("motDePasse");
	        return cuistot1;
		}
	@Test
	public void testSave() {
		Cuisinier cuistot1 = getcuisot();
		cuisinierService.save(cuistot1);
		assertNotNull(cuisinierService.byId(cuistot1.getId()));
		
	}

	@Test
	public void testDelete() {
		Cuisinier cuistot1 = getcuisot();
		cuisinierService.delete(cuistot1);
		assertNull(cuisinierService.byId(cuistot1.getId()));
	}

	@Test
	public void testByLoginOrEmail() {
		 Cuisinier cuistot2 = new Cuisinier("test", "test");
		 cuistot2.setLogin("testlogin");
		 cuistot2.setEmail("test@hotmail.com");
		 cuisinierService.save(cuistot2);
		 assertNotNull(cuisinierService.byLoginOrEmail(cuistot2.getLogin()));
		 assertNotNull(cuisinierService.byLoginOrEmail(cuistot2.getEmail()));
	}

}
