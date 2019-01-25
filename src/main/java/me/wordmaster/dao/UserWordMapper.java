package me.wordmaster.dao;

import me.wordmaster.model.TopUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

public interface UserWordMapper {

    // TODO: make this query work for both H2 and PostgreSql
    @Select("SELECT cnt FROM ( " +
            "  SELECT updated, COUNT(1) as cnt FROM userword uw " +
            "  INNER JOIN appuser u ON uw.userid = u.id " +
            "  WHERE u.username = #{username} " +
            "  GROUP BY updated " +
            ") v " +
            "WHERE v.updated > #{prevdate} " +
            "ORDER BY v.updated asc")
    List<Integer> getLast7DayProgress(@Param("username") String username, @Param("prevdate") Date prevdate);

    @Select("SELECT username, cnt as count FROM ( " +
            "  SELECT u.username AS username, count(1) AS cnt FROM appuser u " +
            "  INNER JOIN userword uw ON u.id = uw.userid " +
            "  GROUP BY u.username " +
            "  ORDER BY count(1) DESC " +
            ") v LIMIT 5")
    List<TopUser> getTopUser();
}
