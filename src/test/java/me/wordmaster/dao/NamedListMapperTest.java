package me.wordmaster.dao;

import me.wordmaster.model.List;
import me.wordmaster.model.Word;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListMapperTest {
    @Autowired
    private ListMapper mapper;

    @Test
    public void testCreateList() {
        List list = new List();
        list.setAuthor("abc");
        list.setTitle("abc list");
        list.setIsbn("12121");
        mapper.createList(list);
    }

    @Test
    public void testGetListByTitle() {
        List list = new List();
        list.setAuthor("abc");
        list.setTitle("abc list 2");
        list.setIsbn("12121");
        mapper.createList(list);

        list = mapper.getListByTitle("abc list 2");
        assertNotNull(list);
        assertEquals("abc", list.getAuthor());
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
        java.util.List<List> lists = mapper.listListByWord("c");
        assertNotNull(lists);
        assertEquals(1, lists.size());
    }

    @Test
    public void testListAllLists() {
        java.util.List<List> lists = mapper.listLists();
        assertNotNull(lists);
        assertTrue(lists.size() > 0);
    }

    @Test
    public void testListWordByList() {
        java.util.List<Word> list = mapper.listWordByList(1L);
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }
}