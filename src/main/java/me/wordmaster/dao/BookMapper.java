package me.wordmaster.dao;

import me.wordmaster.model.Book;
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
}
