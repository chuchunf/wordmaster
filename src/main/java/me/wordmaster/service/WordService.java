package me.wordmaster.service;

import me.wordmaster.dao.WordMapper;
import me.wordmaster.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {
    @Autowired
    private WordMapper mapper;

    public List<Word> getNext10Words(String username) {
        return mapper.next10Words(username);
    }
}
