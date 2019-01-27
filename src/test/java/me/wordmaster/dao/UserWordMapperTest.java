package me.wordmaster.dao;

import me.wordmaster.model.TopUser;
import me.wordmaster.model.UserWord;
import me.wordmaster.model.Word;
import me.wordmaster.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserWordMapperTest {
    @Autowired
    private UserWordMapper mapper;

    @Test
    public void testGetLast7DayProgress() {
        LocalDate localdate = LocalDate.now().minusDays(10);
        Date date = Date.valueOf(localdate);
        String datestr = DateUtils.toYYYYMMDD(date);
        List<Integer> list = mapper.getLast7DayProgress("test", datestr);
        assertNotNull(list);
    }

    @Test
    public void testTopUser() {
        List<TopUser> topusers = mapper.getTopUser();
        assertNotNull(topusers);
        assertEquals(1, topusers.size());
    }

    @Test
    public void testListWordsByDay() {
        List<Word> words = mapper.listWordsByDay("user", "20190101", 3);
        assertNotNull(words);
        assertFalse(words.isEmpty());
    }

    @Test
    public void testGetUserWord() {
        UserWord userword = mapper.getUserWord(1, "a");
        assertNotNull(userword);
    }

    @Test
    public void testUpdateUserWord() {
        UserWord userword = mapper.getUserWord(1, "a");
        assertNotNull(userword);

        userword.setAttempt(1);
        mapper.updateUserWord(userword);

        userword = mapper.getUserWord(1, "a");
        assertNotNull(userword);
        assertSame(1, userword.getAttempt());
    }

    @Test
    public void testInsertUserWord() {
        UserWord userword = new UserWord();
        userword.setUserid(2);
        userword.setWord("b");
        userword.setAttempt(1);
        userword.setMastery(1);
        userword.setStar("Y");
        userword.setStatus("A");
        userword.setCreated("20190102");
        userword.setUpdated("20190102");
        mapper.insertUserWord(userword);
    }
}