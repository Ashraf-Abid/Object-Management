package net.abid.journalApp.service;

import net.abid.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest //it is needed to start the context
public class UserServiceTests {
    @Autowired
    private UserRepo userRepo;
    @Test
    public void testFindByUsername(){
     //assertEquals(3, 2 + 1);
        assertNotNull(userRepo.findByusername("abid"));
    }

}
