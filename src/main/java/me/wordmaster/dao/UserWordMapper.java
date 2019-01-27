package me.wordmaster.dao;

import me.wordmaster.model.TopUser;
import me.wordmaster.model.UserWord;
import me.wordmaster.model.Word;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserWordMapper {

    @Select("SELECT cnt FROM ( " +
            "  SELECT updated, COUNT(1) as cnt FROM userword uw " +
            "  INNER JOIN appuser u ON uw.userid = u.id " +
            "  WHERE u.username = #{username} " +
            "  GROUP BY updated " +
            ") v " +
            "WHERE v.updated > #{prevdate} " +
            "ORDER BY v.updated asc")
    List<Integer> getLast7DayProgress(@Param("username") String username, @Param("prevdate") String prevdate);

    @Select("SELECT username, cnt as count FROM ( " +
            "  SELECT u.username AS username, count(1) AS cnt FROM appuser u " +
            "  INNER JOIN userword uw ON u.id = uw.userid " +
            "  GROUP BY u.username " +
            "  ORDER BY count(1) DESC " +
            ") v LIMIT 5")
    List<TopUser> getTopUser();

    @Select("SELECT ss.word, ee.category, ss.definition FROM sense ss " +
            "INNER JOIN entry ee ON ss.word = ee.word AND ss.seqno = ee.seqno " +
            "INNER JOIN userword uw on uw.word = ss.word " +
            "INNER JOIN appuser u ON uw.userid = u.id " +
            "WHERE u.username = #{username} " +
            "AND uw.updated = #{date} " +
            "AND uw.mastery < #{level} " +
            "AND ss.id IN ( " +
            "  SELECT min(s.id) as id FROM sense s " +
            "  INNER JOIN ( " +
            "    SELECT e.word as word, min(e.seqno) as seqno FROM entry e GROUP by e.word " +
            "  ) ve ON s.word = ve.word AND s.seqno = ve.seqno " +
            "  GROUP BY s.word " +
            ")")
    List<Word> listWordsByDay(@Param("username") String username, @Param("date") String date, @Param("level") int level);

    @Select("SELECT * FROM userword WHERE userid=#{userid} AND word=#{word}")
    UserWord getUserWord(@Param("userid") Integer userid, @Param("word") String word);

    @Update("UPDATE userword SET " +
            "   star    = #{userword.star}, " +
            "   attempt = #{userword.attempt}, " +
            "   mastery = #{userword.mastery}, " +
            "   status  = #{userword.status}, " +
            "   updated = #{userword.updated} " +
            "WHERE userid=#{userword.userid} " +
            "AND word=#{userword.word} ")
    void updateUserWord(@Param("userword") UserWord userword);

    @Insert("INSERT INTO userword (userid,word,star,attempt,mastery,status,created,updated) VALUES (" +
            "   #{userword.userid}," +
            "   #{userword.word}," +
            "   #{userword.star}," +
            "   #{userword.attempt}," +
            "   #{userword.mastery}, " +
            "   #{userword.status}, " +
            "   #{userword.created}, " +
            "   #{userword.updated}" +
            ")")
    void insertUserWord(@Param("userword") UserWord userword);
}
