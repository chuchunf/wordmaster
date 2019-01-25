package me.wordmaster.dao;

import me.wordmaster.model.Word;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WordMapper {
    @Select("")
    List<Word> next10Words(@Param("username") String username);
}
