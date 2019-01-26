package me.wordmaster.dao;

import me.wordmaster.model.Word;
import me.wordmaster.model.WordEntry;
import me.wordmaster.model.WordSense;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    public void testListWordEntries() {
        List<WordEntry> list = mapper.listWordEntries("a");
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    public void testListWordSenses() {
        List<WordSense> list = mapper.listWordSenses("a");
        assertNotNull(list);
        assertEquals(3, list.size());
    }

    @Test
    public void testListSynonyms() {
        List<String> list = mapper.listSynoynms("a");
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    public void testListAntonym() {
        List<String> list = mapper.listAntonym("a");
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    public void testListDerived() {
        List<String> list = mapper.findDerivedWords("a");
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    public void testGetRandomTest() {
        List<String> list = mapper.getRandomText("a", "%a%");
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    public void testRandomDefintion() {
        List<String> list = mapper.getRandomDefinition("a");
        assertNotNull(list);
        assertEquals(3, list.size());
    }

    @Test
    public void testGetFirstDefinition() {
        String str = mapper.getFirstDefinition("a");
        assertNotNull(str);
        assertEquals("definition of a 001", str);
    }
}