package me.wordmaster.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * mapper for those SQL cannot run on H2 .. :(
 */
public interface OtherMapper {
    @Select("SELECT w.word FROM word w " +
            "WHERE w.word NOT IN ( " +
            " SELECT syn.synonym FROM sense s " +
            " INNER JOIN synonym syn on s.thesaurus = syn.linkid " +
            " WHERE s.word=#{word} " +
            ") " +
            "AND levenshtein(w.word, #{word}) <= 3 " +
            "ORDER BY levenshtein(w.word, #{word}) " +
            "LIMIT 20 ")
    List<String> getLookAlike(@Param("word") String word);
}
