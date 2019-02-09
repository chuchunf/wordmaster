package me.wordmaster.service;

import me.wordmaster.dao.WordMapper;
import me.wordmaster.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotSupportedException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class SearchService {
    private static final Logger LOGGER = Logger.getLogger(SearchService.class.getName());

    @Autowired
    private WordMapper mapper;

    public List<Word> searchByProgress(String username, String start, String end, Boolean star, Integer mastery) {
        throw new NotSupportedException();
    }

    public List<Word> searchByBook(String book) {
        throw new NotSupportedException();
    }
}
