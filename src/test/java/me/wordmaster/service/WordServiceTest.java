package me.wordmaster.service;

import me.wordmaster.dao.*;
import me.wordmaster.model.AppUser;
import me.wordmaster.model.Book;
import me.wordmaster.model.Session;
import me.wordmaster.model.Word;
import me.wordmaster.vo.AnswerVO;
import me.wordmaster.vo.BookWordVO;
import me.wordmaster.vo.QuestionVO;
import me.wordmaster.vo.WordVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordServiceTest {
    @MockBean
    private WordMapper worddao;
    @MockBean
    private OtherMapper otherdao;
    @MockBean
    private UserMapper userdao;
    @MockBean
    private UserWordMapper userworddao;
    @MockBean
    private SessionMapper sessiondao;
    @MockBean
    private BookMapper bookdao;

    @Autowired
    private WordService service;

    @Before
    public void setup() {
        when(worddao.next10Words("user")).thenReturn(new ArrayList());
        when(worddao.findDerivedWords("word")).thenReturn(new ArrayList());
        when(worddao.listSynoynms("word")).thenReturn(new ArrayList());
        when(worddao.listAntonym("word")).thenReturn(new ArrayList());
        when(worddao.listWordEntries("word")).thenReturn(new ArrayList());
        when(worddao.listWordSenses("word")).thenReturn(new ArrayList());

        when(worddao.getRandomText("a", "%a%")).thenReturn(Arrays.asList("a 1", "a 2", "a 3"));

        when(otherdao.getLookAlike("a")).thenReturn(Arrays.asList("b", "c", "d"));
        when(worddao.getRandomDefinition("a")).thenReturn(Arrays.asList("defintion a 1", "definition a 2", "definition a 3"));

        AppUser user = new AppUser();
        user.setUsername("test");
        user.setId(1L);
        when(userdao.getUser("user")).thenReturn(user);

        Session session = new Session();
        session.setUserid(1L);
        session.setId("20190101");
        when(sessiondao.getSession("20190101", 1L)).thenReturn(session);

        Book book = new Book();
        book.setId(1L);
        book.setTitle("title1");
        when(bookdao.listBookByWord("a")).thenReturn(Arrays.asList(book));
        when(bookdao.listBooks()).thenReturn(Arrays.asList(book));
        when(bookdao.getBookByTitle("title1")).thenReturn(book);
        doNothing().when(bookdao).addBookWord(isA(Long.class), isA(String.class));
        doNothing().when(bookdao).deleteBookWord(isA(Long.class), isA(String.class));
    }

    @Test
    public void testGetNext10Words() {
        List<Word> words = service.getNext10Words("user");
        assertNotNull(words);
    }

    @Test
    public void testGetWordDetails() {
        WordVO vo = service.getWorDDetails("word");
        assertNotNull(vo);
    }

    @Test
    public void testGetQuestioin() {
        List<QuestionVO> vos = service.getQuestion(Arrays.asList("a"));
        assertNotNull(vos);
    }

    @Test
    public void testAnswer() {
        AnswerVO vo = new AnswerVO();
        vo.setWord("a");
        vo.setResult(true);
        List<AnswerVO> list = Arrays.asList(vo);
        service.updateRecord("user", list);
    }

    @Test
    public void testUpdateBookWord() {
        BookWordVO vo1 = new BookWordVO();
        vo1.setWord("a");
        vo1.setBook("title1");

        List<BookWordVO> list = Arrays.asList(vo1);
        service.updateBookWord(list);
    }
}