package me.wordmaster.dao;

import me.wordmaster.model.AppUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper usermapper;

    @Test
    public void testLogin() {
        AppUser user = usermapper.userLogin("user", "pass");
        assertNotNull(user);
    }
}