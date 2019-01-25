package me.wordmaster.resource;

import me.wordmaster.vo.UserVO;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserResourceIntegratedTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testLogin() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", "user");
        map.add("password", "pass");
        ResponseEntity<UserVO> entity = restTemplate.postForEntity("/api/user/login", map, UserVO.class);
        assertSame(HttpStatus.OK, entity.getStatusCode());
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

    // TODO: IT for topuse and last7days
}
