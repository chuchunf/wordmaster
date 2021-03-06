package me.wordmaster.dao;

import me.wordmaster.model.AppUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    // Column name, not hard code password
    @SuppressWarnings("squid:S2068")
    @Select("SELECT * FROM appuser WHERE username=#{username} AND password=#{hash}")
    AppUser userLogin(@Param("username") String username, @Param("hash") String hash);

    @Select("SELECT * FROM appuser WHERE username=#{username}")
    AppUser getUser(@Param("username") String username);
}
