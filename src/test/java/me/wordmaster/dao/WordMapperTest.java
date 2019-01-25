package me.wordmaster.dao;

import me.wordmaster.model.Word;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordMapperTest {
    @Autowired
    private WordMapper mapper;

    @Test
    public void testNext10Words() {
        List<Word> words = mapper.next10Words("user");
        assertNotNull(words);
        assertFalse(words.isEmpty());
    }
}