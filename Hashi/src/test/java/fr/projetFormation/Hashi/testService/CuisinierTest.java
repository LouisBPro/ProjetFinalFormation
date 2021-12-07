package fr.projetFormation.Hashi.testService;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.projetFormation.Hashi.entities.Cuisinier;
import fr.projetFormation.Hashi.services.CuisinierService;

@SpringBootTest
public class CuisinierTest {
	 @Autowired
	    CuisinierService cuisinierService;
	 
	 private Cuisinier getcuisot() {
		 Cuisinier cuistot1 = new Cuisinier();
	        cuistot1.setNom("Baron");
	        cuistot1.setPrenom("Louis");
	        return cuistot1;
		}
	@Test
	public void testSave() {
		Cuisinier cuistot1 = getcuisot();
		cuisinierService.create(cuistot1);
		assertNotNull(cuisinierService.byId(cuistot1.getId()));
		
	}

	@Test
	public void testDelete() {
		Cuisinier cuistot1 = getcuisot();
		cuisinierService.delete(cuistot1);
		assertNull(cuisinierService.byId(cuistot1.getId()));
	}

	

}
