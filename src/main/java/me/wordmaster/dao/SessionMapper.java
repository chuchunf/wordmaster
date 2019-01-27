package me.wordmaster.dao;

import me.wordmaster.model.Session;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SessionMapper {
    @Select("SELECT * FROM session WHERE id=#{date} AND userid=#{userid}")
    Session getSession(@Param("date") String date, @Param("userid") Integer userid);

    @Update("UPDATE session SET" +
            "   practiced = #{session.practiced}, " +
            "   learned   = #{session.learned}, " +
            "   mastered  = #{session.mastered} " +
            "WHERE id = #{session.id}" +
            "AND userid = #{session.userid}")
    void updateSession(@Param("session") Session session);

    @Insert("INSERT INTO session(id, userid, practiced, learned, mastered) VALUES(" +
            "#{session.id}, " +
            "#{session.userid}, " +
            "#{session.practiced}, " +
            "#{session.learned}, " +
            "#{session.mastered}" +
            ")")
    int insertSession(@Param("session") Session session);
}
