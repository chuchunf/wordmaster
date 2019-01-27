package me.wordmaster.dao;

import me.wordmaster.model.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionMapperTest {
    @Autowired
    private SessionMapper mapper;

    @Test
    public void testInsert() {
        Session session = new Session();
        session.setId("20190102");
        session.setUserid(1);
        session.setLearned(1);
        session.setPracticed(1);
        session.setMastered(1);
        mapper.insertSession(session);
    }

    @Test
    public void testSelect() {
        Session session = mapper.getSession("20190101", 1);
        assertNotNull(session);
    }

    @Test
    public void testUpdate() {
        Session session = mapper.getSession("20190101", 1);
        assertNotNull(session);

        session.setMastered(10);
        mapper.updateSession(session);

        session = mapper.getSession("20190101", 1);
        assertNotNull(session);
        assertSame(10, session.getMastered());
    }
}