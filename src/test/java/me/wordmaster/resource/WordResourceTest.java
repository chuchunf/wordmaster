package me.wordmaster.resource;

import me.wordmaster.model.UserWord;
import me.wordmaster.security.JWTService;
import me.wordmaster.service.WordService;
import me.wordmaster.vo.AnswerVO;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
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
        when(service.getWorDDetails("a")).thenReturn(new WordVO());
        when(service.getQuestion(Arrays.asList("a"))).thenReturn(new ArrayList());

        AnswerVO vo = new AnswerVO();
        vo.setWord("a");
        vo.setResult(true);
        List<AnswerVO> list = Arrays.asList(vo);
        doNothing().when(service).updateRecord("user", Arrays.asList(vo));

        doNothing().when(service).updateListWord(isA(List.class));
        doNothing().when(service).updateUserWord(isA(String.class), isA(UserWord.class));
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
    public void testUpdateListWords() {
        String jwttoken = jwtservice.createToken("user");
        assertNotNull(jwttoken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwttoken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>("[{\"word\":\"a\",\"list\":\"title1\"}]", headers);
        ResponseEntity<String> result = restTemplate.exchange("/api/word/listword", HttpMethod.POST, request, String.class);
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