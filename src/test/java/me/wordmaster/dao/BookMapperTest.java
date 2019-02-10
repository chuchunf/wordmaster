package me.wordmaster.dao;

import me.wordmaster.model.Book;
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
public class BookMapperTest {
    @Autowired
    private BookMapper mapper;

    @Test
    public void testCreateBook() {
        Book book = new Book();
        book.setAuthor("abc");
        book.setTitle("abc book");
        book.setIsbn("12121");
        mapper.createBook(book);
    }

    @Test
    public void testGetBookByTitle() {
        Book book = new Book();
        book.setAuthor("abc");
        book.setTitle("abc book 2");
        book.setIsbn("12121");
        mapper.createBook(book);

        book = mapper.getBookByTitle("abc book 2");
        assertNotNull(book);
        assertEquals("abc", book.getAuthor());
    }

    @Test
    public void testInsertBookWord() {
        mapper.addBookWord(1L, "a");
    }

    @Test
    public void testDeleteBookWrod() {
        mapper.addBookWord(1L, "b");
        mapper.deleteBookWord(1L, "b");
    }

    @Test
    public void testListBookByWord() {
        mapper.addBookWord(1L, "c");
        List<Book> books = mapper.listBookByWord("c");
        assertNotNull(books);
        assertEquals(1, books.size());
    }

    @Test
    public void testListAllBooks() {
        List<Book> books = mapper.listBooks();
        assertNotNull(books);
        assertTrue(books.size() > 0);
    }

    @Test
    public void testListWordByBook() {
        List<Word> list = mapper.listWordByBook(1L);
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }
}