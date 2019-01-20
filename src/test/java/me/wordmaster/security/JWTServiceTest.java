package me.wordmaster.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JWTServiceTest {
    @Autowired
    private JWTService service;

    @Test
    public void testHandleToken() {
        String token = service.createToken("testuser");
        assertNotNull(token);

        Optional<UserToken> user = service.getUserToken(token);
        assertTrue(user.isPresent());
        assertEquals("testuser", user.get().getUsername());
    }
}
