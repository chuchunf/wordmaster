package me.wordmaster.service;

import me.wordmaster.dao.WordMapper;
import me.wordmaster.model.Word;
import me.wordmaster.model.WordEntry;
import me.wordmaster.model.WordSense;
import me.wordmaster.vo.QuestionVO;
import me.wordmaster.vo.WordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class WordService {
    @Autowired
    private WordMapper mapper;

    public List<Word> getNext10Words(String username) {
        return mapper.next10Words(username);
    }

    public WordVO getWorDDetails(String word) {
        final WordVO vo = new WordVO();

        vo.setWord(word);
        vo.setSynonyms(mapper.listSynoynms(word));
        vo.setAcronyms(mapper.listAntonym(word));
        vo.setDerived(mapper.findDerivedWords(word));

        List<WordEntry> entries = mapper.listWordEntries(word);
        List<WordSense> senses = mapper.listWordSenses(word);
        entries.forEach(entry -> {
            List<WordSense> filtered = senses.stream()
                    .filter(sense -> sense.getSeqno().equals(entry.getSeqno()))
                    .collect(Collectors.toList());
            entry.setSenses(filtered);
        });
        vo.setEntries(entries);

        return vo;
    }

    public List<QuestionVO> getQuestion(List<String> words) {
        return words.stream()
                .map(this::createRandomQuestion)
                .collect(Collectors.toList());
    }

    private QuestionVO createRandomQuestion(String word) {
        if (new Random().nextBoolean()) return createClozeQuestion(word);
        else if (new Random().nextBoolean()) return createChooseWordQuestion(word);
        else return createChooseSentenseQuestion(word);
    }

    private QuestionVO createChooseWordQuestion(String word) {
        return null;
    }

    private QuestionVO createChooseSentenseQuestion(String word) {
        return null;
    }

    private QuestionVO createClozeQuestion(String word) {
        return null;
    }
}
