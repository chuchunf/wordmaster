package me.wordmaster.resource;

import me.wordmaster.security.JWTService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchResourceIntegratedTest {
    @Autowired
    private JWTService jwtservice;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSearchTitle() {
        String jwttoken = jwtservice.createToken("user");
        assertNotNull(jwttoken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwttoken);

        HttpEntity<String> request = new HttpEntity<>("parameters", headers);
        ResponseEntity<List> result = restTemplate.exchange("/api/search/title/title1", HttpMethod.GET, request, List.class);
        assertNotNull(result);
    }
}
