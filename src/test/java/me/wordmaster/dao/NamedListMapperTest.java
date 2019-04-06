package me.wordmaster.dao;

import me.wordmaster.model.NamedList;
import me.wordmaster.model.Word;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NamedListMapperTest {
    @Autowired
    private ListMapper mapper;

    @Test
    public void testCreateList() {
        NamedList namedList = new NamedList();
        namedList.setAuthor("abc");
        namedList.setTitle("abc namedList");
        namedList.setIsbn("12121");
        mapper.createList(namedList);
    }

    @Test
    public void testGetListByTitle() {
        NamedList namedList = new NamedList();
        namedList.setAuthor("abc");
        namedList.setTitle("abc namedList 2");
        namedList.setIsbn("12121");
        mapper.createList(namedList);

        namedList = mapper.getListByTitle("abc namedList 2");
        assertNotNull(namedList);
        assertEquals("abc", namedList.getAuthor());
    }

    @Test
    public void testInsertListWord() {
        mapper.addListWord(1L, "a");
    }

    @Test
    public void testDeleteListWord() {
        mapper.addListWord(1L, "b");
        mapper.deleteListWord(1L, "b");
    }

    @Test
    public void testListListByWord() {
        mapper.addListWord(1L, "c");
        List<NamedList> namedLists = mapper.listListByWord("c");
        assertNotNull(namedLists);
        assertEquals(1, namedLists.size());
    }

    @Test
    public void testListAllLists() {
        List<NamedList> namedLists = mapper.listLists();
        assertNotNull(namedLists);
        assertTrue(namedLists.size() > 0);
    }

    @Test
    public void testListWordByList() {
        List<Word> list = mapper.listWordByList(1L);
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }
}