package me.wordmaster.service;

import me.wordmaster.dao.ListMapper;
import me.wordmaster.dao.WordMapper;
import me.wordmaster.model.NamedList;
import me.wordmaster.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SearchService {
    private static final Logger LOGGER = Logger.getLogger(SearchService.class.getName());

    @Autowired
    private WordMapper mapper;
    @Autowired
    private ListMapper listmapper;

    public List searchByProgress(String username, String start, String end, Boolean star, Integer mastery) {
        throw new NotSupportedException();
    }

    public List<Word> searchByList(String title) {
        NamedList namedList = listmapper.getListByTitle(title);
        if (namedList == null) {
            LOGGER.log(Level.INFO, "search a non-existence namedList {0}", title);
            return new ArrayList<>();
        }

        return listmapper.listWordByList(namedList.getId());
    }
}
