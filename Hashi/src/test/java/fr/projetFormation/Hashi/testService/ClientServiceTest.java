package fr.projetFormation.Hashi.testService;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import fr.projetFormation.Hashi.services.ClientService;

@SpringBootTest
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
        // assertNotNull(clientService.byId(205L));
        //assertNull(clientService.byId(200L));

        // assertNotNull(clientService.byLoginOrEmail("barlou"));
        // assertNotNull(clientService.byLoginOrEmail("louis@hotmail.com"));
        //assertNull(clientService.byLoginOrEmail("bar"));

        // clientService.delete(clientService.byId(205L));
        //assertNull(clientService.byId(204L));
    }
}