package me.wordmaster.service;

import me.wordmaster.dao.UserMapper;
import me.wordmaster.model.AppUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @MockBean
    private UserMapper userdao;

    @Autowired
    private UserService service;

    @Before
    public void setup() {
        AppUser test = new AppUser();
        test.setUsername("user");
        when(userdao.userLogin("user", "05d49692b755f99c4504b510418efeeeebfd466892540f27acf9a31a326d6504")).thenReturn(test);
        when(userdao.userLogin("null", "2c7bddafa6f824cb0e682091aa1d9ca392883cb1f5bcff95389adc9feae77fcd")).thenReturn(null);
        when(userdao.getUser("user1")).thenReturn(test);
    }

    @Test
    public void testLogin() {
        AppUser user = service.login("null", "null");
        user = service.login("user", "pass");
        assertNotNull(user);
    }

    @Test
    public void testGetUser() {
        AppUser user = service.getUserByName("user1");
        assertNotNull(user);
    }
}