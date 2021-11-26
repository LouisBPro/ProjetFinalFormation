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
import projetFinal.entity.Client;
import projetFinal.entity.Gerant;
import projetFinal.entity.LigneCarte;
import projetFinal.entity.LigneCartePk;
import projetFinal.entity.Plat;
import projetFinal.entity.Restaurant;
import projetFinal.services.ClientService;
import projetFinal.services.CuisinierService;
import projetFinal.services.GerantService;
import projetFinal.services.PlatService;
import projetFinal.services.RestaurantService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Rollback
public class ClientServiceTest {

    @Autowired
    ClientService clientService;

    @Test
    public void testClient() {

        // Client client1 = new Client();
        // client1.setNom("Baron");
        // client1.setPrenom("Louis");
        // client1.setLogin("barlou");
        // client1.setPassword("motDePasse");
        // client1.setEmail("louis@hotmail.com");

        // clientService.save(client1);
        assertNotNull(clientService.byId(205L));
        //assertNull(clientService.byId(200L));

        assertNotNull(clientService.byLoginOrEmail("barlou"));
        assertNotNull(clientService.byLoginOrEmail("louis@hotmail.com"));
        //assertNull(clientService.byLoginOrEmail("bar"));

        clientService.delete(clientService.byId(205L));
        //assertNull(clientService.byId(204L));
    }
}