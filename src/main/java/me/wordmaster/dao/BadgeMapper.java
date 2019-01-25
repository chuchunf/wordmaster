package me.wordmaster.dao;

import me.wordmaster.model.Badge;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BadgeMapper {
    @Select("SELECT b.* FROM badge b INNER JOIN userbadge ub ON b.id = ub.badgeid WHERE ub.userid=#{userid}")
    List<Badge> listBadgesByUser(@Param("userid") Long userid);
}
