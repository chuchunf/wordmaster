package me.wordmaster.dao;

import me.wordmaster.model.TopUser;
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
}