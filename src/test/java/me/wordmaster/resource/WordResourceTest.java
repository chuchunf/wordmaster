package me.wordmaster.resource;

import me.wordmaster.security.JWTService;
import me.wordmaster.service.WordService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WordResourceTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JWTService jwtservice;

    @MockBean
    private WordService service;

    @Before
    public void setup() {
        when(service.getNext10Words("user")).thenReturn(new ArrayList());
    }

    @Test
    public void testGetNext10Wrods() {
        String jwttoken = jwtservice.createToken("user");
        assertNotNull(jwttoken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwttoken);

        HttpEntity<String> request = new HttpEntity<>("parameters", headers);
        ResponseEntity<List> result = restTemplate.exchange("/api/word/next10words", HttpMethod.GET, request, List.class);
        assertNotNull(result);
    }
}