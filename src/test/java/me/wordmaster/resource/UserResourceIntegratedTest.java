package me.wordmaster.resource;

import me.wordmaster.security.JWTService;
import me.wordmaster.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserResourceIntegratedTest {
    @Autowired
    private JWTService jwtservice;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testLogin() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", "user");
        map.add("password", "pass");
        ResponseEntity<UserVO> entity = restTemplate.postForEntity("/api/user/login", map, UserVO.class);
        assertSame(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(entity.getBody());
        assertNotNull(entity.getBody().getUser());
        assertEquals("user", entity.getBody().getUser().getUsername());
    }

    @Test
    public void testLoginFail() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", "null");
        map.add("password", "null");
        ResponseEntity<UserVO> entity = restTemplate.postForEntity("/api/user/login", map, UserVO.class);
        assertSame(HttpStatus.FORBIDDEN, entity.getStatusCode());
    }

    @Test
    public void testTopUser() {
        String jwttoken = jwtservice.createToken("user");
        assertNotNull(jwttoken);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwttoken);

        HttpEntity<String> request = new HttpEntity<>("parameters", headers);
        ResponseEntity<List> result = restTemplate.exchange("/api/user/topuser", HttpMethod.GET, request, List.class);
        assertNotNull(result);
    }

    @Test
    public void testLast7DaysProgress() {
        String jwttoken = jwtservice.createToken("user");
        assertNotNull(jwttoken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwttoken);

        HttpEntity<String> request = new HttpEntity<>("parameters", headers);
        ResponseEntity<List> result = restTemplate.exchange("/api/user/last7days", HttpMethod.GET, request, List.class);
        assertNotNull(result);
    }

    @Test
    public void testWordsInPreviousDay() {
        String jwttoken = jwtservice.createToken("user");
        assertNotNull(jwttoken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwttoken);

        HttpEntity<String> request = new HttpEntity<>("parameters", headers);
        ResponseEntity<List> result = restTemplate.exchange("/api/user/prevwords?day=20190101", HttpMethod.GET, request, List.class);
        assertNotNull(result);
    }
}
