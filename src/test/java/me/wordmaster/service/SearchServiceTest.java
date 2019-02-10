package me.wordmaster.service;

import me.wordmaster.dao.BookMapper;
import me.wordmaster.model.Book;
import me.wordmaster.model.Word;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {
    @MockBean
    private BookMapper bookdao;

    @Autowired
    private SearchService service;

    @Before
    public void setup() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("title1");

        when(bookdao.getBookByTitle(isA(String.class))).thenReturn(book);
        when(bookdao.listWordByBook(isA(Long.class))).thenReturn(Arrays.asList());
    }

    @Test
    public void testSearchWordByTitle() {
        List<Word> list = service.searchByBook("title1");
        assertNotNull(list);
    }
}