package me.wordmaster.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
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
        String jwt = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJ3b3JkbWFzdGVyIiwic3ViIjoidGVzdHVzZXIiLCJpYXQiOjE1NDc5NTY4OTIsImV4cCI6MTU0Nzk2MDQ5Mn0.ASz-FsfhwfBCPt9_ZB1tEOxddiH8l0TfNtWfmWBHQd_xZn0NJuhh4wAGtMQl5m5K-NDT7KHRG-L3ZvW-NuEbjw";
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", jwt);
        Optional<UserToken> user = service.getUserToken(request);

        assertTrue(user.isPresent());
        assertEquals("testuser", user.get().getUsername());
    }
}
