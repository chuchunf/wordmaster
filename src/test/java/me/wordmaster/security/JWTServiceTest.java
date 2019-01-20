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
    public void testCreateToken() {
        String token = service.createToken("testuser");
        assertNotNull(token);
    }

    @Test
    public void testGetUser() {
        String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJ3b3JkbWFzdGVyIiwic3ViIjoidGVzdHVzZXIiLCJpYXQiOjE1NDc5NjU5MDYsImV4cCI6MTU0Nzk2OTUwNn0.pAGbROz4gj4iSh7g2TXYfmEiFNjXpRxyr8dEvQ20YSlnR5p0elsD0OtMcsRSlc7em74aV16kiuk_FwrJjFPRqQ";
        Optional<UserToken> user = service.getUserToken(jwt);

        assertTrue(user.isPresent());
        assertEquals("testuser", user.get().getUsername());
    }
}
