package me.wordmaster.service;

import me.wordmaster.dao.ListMapper;
import me.wordmaster.model.NamedList;
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
    private ListMapper listdao;

    @Autowired
    private SearchService service;

    @Before
    public void setup() {
        NamedList namedList = new NamedList();
        namedList.setId(1L);
        namedList.setTitle("title1");

        when(listdao.getListByTitle(isA(String.class))).thenReturn(namedList);
        when(listdao.listWordByList(isA(Long.class))).thenReturn(Arrays.asList());
    }

    @Test
    public void testSearchWordByTitle() {
        List list = service.searchByList("title1");
        assertNotNull(list);
    }
}