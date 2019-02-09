package me.wordmaster.resource;

import me.wordmaster.dao.OtherMapper;
import me.wordmaster.security.JWTService;
import me.wordmaster.vo.WordVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WordResourceIntegratedTest {
    @Autowired
    private JWTService jwtservice;

    @Autowired
    private TestRestTemplate restTemplate;

    // Mock this mapper as H2 doesn't work with its queries
    @MockBean
    private OtherMapper otherdao;

    @Before
    public void setup() {
        when(otherdao.getLookAlike("a")).thenReturn(Arrays.asList("b", "c", "d"));
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

    @Test
    public void testGetDetails() {
        String jwttoken = jwtservice.createToken("user");
        assertNotNull(jwttoken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwttoken);

        HttpEntity<String> request = new HttpEntity<>("parameters", headers);
        ResponseEntity<WordVO> result = restTemplate.exchange("/api/word/details/a", HttpMethod.GET, request, WordVO.class);
        assertNotNull(result);
        WordVO vo = result.getBody();
        assertNotNull(vo);
        assertEquals("a", vo.getWord());
        assertFalse(vo.getAcronyms().isEmpty());
        assertFalse(vo.getSynonyms().isEmpty());
        assertFalse(vo.getEntries().isEmpty());
    }

    @Test
    public void testAskQuestions() {
        String jwttoken = jwtservice.createToken("user");
        assertNotNull(jwttoken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwttoken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>("[\"a\"]", headers);
        ResponseEntity<List> result = restTemplate.exchange("/api/word/ask", HttpMethod.POST, request, List.class);
        assertNotNull(result);
    }

    @Test
    public void testAnswer() {
        String jwttoken = jwtservice.createToken("user");
        assertNotNull(jwttoken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwttoken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>("[{\"word\":\"a\",\"result\":\"true\"}]", headers);
        ResponseEntity<String> result = restTemplate.exchange("/api/word/answer", HttpMethod.POST, request, String.class);
        assertNotNull(result);
    }

    @Test
    public void testUpdateBookWords() {
        String jwttoken = jwtservice.createToken("user");
        assertNotNull(jwttoken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwttoken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>("[{\"word\":\"a\",\"book\":\"title1\"}]", headers);
        ResponseEntity<String> result = restTemplate.exchange("/api/word/bookword", HttpMethod.POST, request, String.class);
        assertNotNull(result);
    }

    @Test
    public void testUpdateUserWord() {
        String jwttoken = jwtservice.createToken("user");
        assertNotNull(jwttoken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwttoken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>("[{\"word\":\"a\",\"userid\":\"1\"}]", headers);
        ResponseEntity<String> result = restTemplate.exchange("/api/word/userword", HttpMethod.POST, request, String.class);
        assertNotNull(result);
    }
}
