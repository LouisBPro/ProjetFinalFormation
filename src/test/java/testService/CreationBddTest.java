package testService;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projetFinal.config.AppConfig;
import projetFinal.entity.Adresse;
import projetFinal.entity.Client;
import projetFinal.services.ClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class CreationBddTest {
	
	@Autowired
	private ClientService clientService;

	@Test
	public void test() {
		Client c = new Client();
		c.setEmail("client.email@restaurant.com");
		c.setNom("Richard");
		c.setPrenom("Quentin");
		c.setLogin("Quentin");
		c.setPassword("password");
		c.setAdresse(new Adresse(1, "rue", "35200", "Rennes"));
		
		assertNotNull(c);
		
		clientService.save(c);
		
		
	}

}
