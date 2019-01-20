package me.wordmaster.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testLogin() {
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("username","user");
        map.add("password", "pass");
        ResponseEntity<String> entity = restTemplate.postForEntity("/api/user/login", map, String.class);
        assertSame(HttpStatus.OK, entity.getStatusCode());
        assertSame("user login", entity.getBody());
    }
}