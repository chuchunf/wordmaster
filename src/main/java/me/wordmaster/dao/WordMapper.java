package me.wordmaster.dao;

import me.wordmaster.model.Word;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WordMapper {
    @Select("SELECT w.word, ee.category, ss.definition FROM word w " +
            "INNER JOIN sense ss ON w.word = ss.word " +
            "INNER JOIN entry ee ON ss.word = ee.word AND ss.seqno = ee.seqno " +
            "AND ss.id IN ( " +
            "  SELECT min(s.id) as id FROM sense s  " +
            "  INNER JOIN ( " +
            "    SELECT e.word as word, min(e.seqno) as seqno FROM entry e GROUP by e.word " +
            "  ) ve ON s.word = ve.word AND s.seqno = ve.seqno " +
            "  GROUP BY s.word " +
            ") " +
            "AND ss.word NOT IN ( " +
            "  SELECT uw.word FROM userword uw " +
            "  INNER JOIN appuser u ON uw.userid = u.id " +
            "  WHERE u.username = #{username} " +
            ")  " +
            "ORDER BY w.seq ASC " +
            "LIMIT 10")
    List<Word> next10Words(@Param("username") String username);
}
