package me.wordmaster.dao;

import me.wordmaster.model.Book;
import me.wordmaster.model.Word;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper {
    @Insert("INSERT INTO book (title,author,isbn) VALUES (#{book.title},#{book.author},#{book.isbn})")
    void createBook(@Param("book") Book book);

    @Select("SELECT * FROM book WHERE title=#{title}")
    Book getBookByTitle(@Param("title") String title);

    @Select("SELECT * FROM book WHERE id IN (SELECT bookid FROM bookword WHERE word=#{word})")
    List<Book> listBookByWord(@Param("word") String word);

    @Insert("INSERT INTO bookword (bookid, word) VALUES (#{bookid},#{word})")
    void addBookWord(@Param("bookid") Long bookid, @Param("word") String word);

    @Delete("DELETE FROM bookword WHERE bookid=#{bookid} AND word=#{word}")
    void deleteBookWord(@Param("bookid") Long bookid, @Param("word") String word);

    @Select("SELECT * FROM book")
    List<Book> listBooks();

    @Select("SELECT w.word, ee.category, ss.definition FROM word w " +
            "INNER JOIN sense ss ON w.word = ss.word " +
            "INNER JOIN entry ee ON ss.word = ee.word AND ss.seqno = ee.seqno " +
            "INNER JOIN bookword bw ON w.word = bw.word " +
            "WHERE ss.id IN ( " +
            "  SELECT min(s.id) as id FROM sense s " +
            "  INNER JOIN ( " +
            "    SELECT e.word as word, min(e.seqno) as seqno FROM entry e GROUP by e.word " +
            "  ) ve ON s.word = ve.word AND s.seqno = ve.seqno " +
            "  GROUP BY s.word " +
            ") " +
            "AND bw.bookid = #{bookid}")
    List<Word> listWordByBook(@Param("bookid") Long bookid);
}
