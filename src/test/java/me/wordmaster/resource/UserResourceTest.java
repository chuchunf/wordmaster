package me.wordmaster.resource;

import me.wordmaster.model.AppUser;
import me.wordmaster.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserService service;

    @Before
    public void setup () {
        AppUser test = new AppUser();
        test.setUsername("user");
        when(service.login("user", "pass")).thenReturn(test);
        when(service.login("null", "null")).thenReturn(null);
    }

    @Test
    public void testLogin() {
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("username","user");
        map.add("password", "pass");
        ResponseEntity<AppUser> entity = restTemplate.postForEntity("/api/user/login", map, AppUser.class);
        assertSame(HttpStatus.OK, entity.getStatusCode());
        assertEquals("user", entity.getBody().getUsername());
    }

    @Test
    public void testLoginFail() {
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("username","null");
        map.add("password", "null");
        ResponseEntity<AppUser> entity = restTemplate.postForEntity("/api/user/login", map, AppUser.class);
        assertSame(HttpStatus.FORBIDDEN, entity.getStatusCode());
    }
}