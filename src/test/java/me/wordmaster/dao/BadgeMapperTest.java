package me.wordmaster.dao;

import me.wordmaster.model.Badge;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BadgeMapperTest {
    @Autowired
    private BadgeMapper badgeMapper;

    @Test
    public void testlistBadgesByUser() {
        List<Badge> list = badgeMapper.listBadgesByUser(1L);
        assertNotNull(list);
        assertEquals(1, list.size());
    }
}