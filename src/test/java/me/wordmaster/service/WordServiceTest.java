package me.wordmaster.service;

import me.wordmaster.dao.WordMapper;
import me.wordmaster.model.Word;
import me.wordmaster.vo.WordVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordServiceTest {
    @MockBean
    private WordMapper worddao;

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
}