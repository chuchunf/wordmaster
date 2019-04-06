package me.wordmaster.dao;

import me.wordmaster.model.NamedList;
import me.wordmaster.model.Word;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ListMapper {
    @Insert("INSERT INTO list (title,author,isbn) VALUES (#{namedList.title},#{namedList.author},#{namedList.isbn})")
    void createList(@Param("namedList") NamedList namedList);

    @Select("SELECT * FROM list WHERE title=#{title}")
    NamedList getListByTitle(@Param("title") String title);

    @Select("SELECT * FROM list WHERE id IN (SELECT listid FROM listword WHERE word=#{word})")
    List<NamedList> listListByWord(@Param("word") String word);

    @Insert("INSERT INTO listword (listid, word) VALUES (#{listid},#{word})")
    void addListWord(@Param("listid") Long listid, @Param("word") String word);

    @Delete("DELETE FROM listword WHERE listid=#{listid} AND word=#{word}")
    void deleteListWord(@Param("listid") Long listid, @Param("word") String word);

    @Select("SELECT * FROM list")
    List<NamedList> listLists();

    @Select("SELECT w.word, ee.category, ss.definition FROM word w " +
            "INNER JOIN sense ss ON w.word = ss.word " +
            "INNER JOIN entry ee ON ss.word = ee.word AND ss.seqno = ee.seqno " +
            "INNER JOIN listword bw ON w.word = bw.word " +
            "WHERE ss.id IN ( " +
            "  SELECT min(s.id) as id FROM sense s " +
            "  INNER JOIN ( " +
            "    SELECT e.word as word, min(e.seqno) as seqno FROM entry e GROUP by e.word " +
            "  ) ve ON s.word = ve.word AND s.seqno = ve.seqno " +
            "  GROUP BY s.word " +
            ") " +
            "AND bw.listid = #{listid}")
    List<Word> listWordByList(@Param("listid") Long listid);
}
