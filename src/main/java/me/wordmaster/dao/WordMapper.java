package me.wordmaster.dao;

import me.wordmaster.model.Word;
import me.wordmaster.model.WordEntry;
import me.wordmaster.model.WordSense;
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

    @Select("SELECT * FROM entry WHERE word=#{word}")
    List<WordEntry> listWordEntries(@Param("word") String word);

    @Select("SELECT * FROM sense ss " +
            "LEFT OUTER JOIN ( " +
            " SELECT s.word AS word, min(sa.text) as sample FROM sample sa " +
            " INNER JOIN sense s on sa.senseid = s.id " +
            "GROUP BY s.word " +
            ") vs on ss.word = vs.word " +
            "WHERE ss.word = #{word}")
    List<WordSense> listWordSenses(@Param("word") String word);

    @Select("SELECT syn.synonym FROM sense s " +
            "INNER JOIN synonym syn on s.thesaurus = syn.linkid " +
            "WHERE s.word=#{word}")
    List<String> listSynoynms(@Param("word") String word);

    @Select("SELECT syn.antonym FROM sense s " +
            "INNER JOIN antonym syn on s.thesaurus = syn.linkid " +
            "WHERE s.word=#{word}")
    List<String> listAntonym(@Param("word") String word);

    @Select("SELECT DISTINCT(word) as word FROM ( " +
            "  SELECT word2 as word FROM matrix WHERE word IN (SELECT word FROM MATRIX WHERE word2=#{word}) " +
            "  UNION ALL  " +
            "  SELECT word2 as word FROM MATRIX WHERE word=#{word} " +
            ") v")
    List<String> findDerivedWords(@Param("word") String word);
}
