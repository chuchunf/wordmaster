package me.wordmaster.service;

import me.wordmaster.dao.OtherMapper;
import me.wordmaster.dao.WordMapper;
import me.wordmaster.model.Word;
import me.wordmaster.model.WordEntry;
import me.wordmaster.model.WordSense;
import me.wordmaster.vo.QuestionVO;
import me.wordmaster.vo.WordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class WordService {
    @Autowired
    private WordMapper mapper;
    @Autowired
    private OtherMapper othermapper;

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
        final QuestionVO vo = new QuestionVO();

        vo.setWord(word);
        vo.setQuestion(mapper.getFirstDefinition(word));

        List<String> alikes = othermapper.getLookAlike(word);
        Collections.shuffle(alikes);
        List<String> choises = new ArrayList<>();
        choises.addAll(alikes.subList(0, 3));
        choises.add(word);
        Collections.shuffle(choises);
        vo.setChoises(choises);

        vo.setAnswer(choises.indexOf(word));

        return vo;
    }

    private QuestionVO createChooseSentenseQuestion(String word) {
        final QuestionVO vo = new QuestionVO();

        vo.setWord(word);
        vo.setQuestion(word);

        List<String> defintions = mapper.getRandomDefinition(word);
        Collections.shuffle(defintions);
        List<String> choises = new ArrayList<>();
        choises.addAll(defintions.subList(0, 3));
        String definition = mapper.getFirstDefinition(word);
        choises.add(definition);
        Collections.shuffle(choises);
        vo.setChoises(choises);

        vo.setAnswer(defintions.indexOf(definition));

        return vo;
    }

    private QuestionVO createClozeQuestion(String word) {
        final QuestionVO vo = new QuestionVO();

        vo.setWord(word);
        List<String> samples = mapper.getRandomText(word, "%" + word + "%");
        Collections.shuffle(samples);
        String question = samples.get(0);
        question = question.replace(word, " (___________) ");
        vo.setQuestion(question);

        List<String> alikes = othermapper.getLookAlike(word);
        Collections.shuffle(alikes);
        List<String> choises = new ArrayList<>();
        choises.addAll(alikes.subList(0, 3));
        choises.add(word);
        Collections.shuffle(choises);
        vo.setChoises(choises);

        vo.setAnswer(choises.indexOf(word));
        return vo;
    }
}
