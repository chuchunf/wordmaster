package me.wordmaster.service;

import me.wordmaster.dao.*;
import me.wordmaster.model.*;
import me.wordmaster.util.DateUtils;
import me.wordmaster.util.Mastery;
import me.wordmaster.vo.AnswerVO;
import me.wordmaster.vo.ListWordVO;
import me.wordmaster.vo.QuestionVO;
import me.wordmaster.vo.WordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class WordService {
    private static final Logger LOGGER = Logger.getLogger(WordService.class.getName());
    private static final Random RANDOM = new Random();

    @Autowired
    private WordMapper mapper;
    @Autowired
    private OtherMapper othermapper;
    @Autowired
    private SessionMapper sessionmapper;
    @Autowired
    private UserMapper usermapper;
    @Autowired
    private UserWordMapper userwordmapper;
    @Autowired
    private ListMapper listmapper;

    public List<Word> getNext10Words(String username) {
        return mapper.next10Words(username);
    }

    public WordVO getWorDDetails(String word) {
        final WordVO vo = new WordVO();

        vo.setWord(word);
        vo.setSynonyms(mapper.listSynoynms(word));
        vo.setAcronyms(mapper.listAntonym(word));
        vo.setDerived(mapper.findDerivedWords(word));

        List<String> lookalike = othermapper.getLookAlike(word);
        if (lookalike != null && !lookalike.isEmpty()) {
            final int len = lookalike.size() - 1 >= 3 ? 3 : lookalike.size() - 1;
            vo.setAlike(lookalike.subList(0, len));
        }

        List<WordEntry> entries = mapper.listWordEntries(word);
        List<WordSense> senses = mapper.listWordSenses(word);
        entries.forEach(entry -> {
            List filtered = senses.stream()
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

    @Transactional
    public void updateRecord(String username, List<AnswerVO> answer) {
        AppUser user = usermapper.getUser(username);
        if (user == null) {
            LOGGER.log(Level.SEVERE, "invaludate user called {0}", username);
            return;
        }
        Long userid = user.getId();

        int learned = 0;
        int practiced = 0;
        int mastered = 0;
        for (AnswerVO vo : answer) {
            UserWord uword = userwordmapper.getUserWord(userid, vo.getWord());
            if (uword == null) {
                uword = new UserWord();
                uword.setUserid(userid);
                uword.setWord(vo.getWord());
                userwordmapper.insertUserWord(uword);
            }
            uword.setAttempt(uword.getAttempt() + 1);
            if (vo.getResult()) {
                uword.setMastery(uword.getMastery() + 1);
                if (uword.getMastery() >= Mastery.MASTERED.getLevel()) {
                    mastered++;
                } else if (uword.getMastery() >= Mastery.TEST1.getLevel()) {
                    learned++;
                } else {
                    practiced++;
                }
            }
            userwordmapper.updateUserWord(uword);
        }

        String today = DateUtils.nowAsYYYYMMDD();
        Session session = sessionmapper.getSession(today, userid);
        if (session == null) {
            session = new Session();
            session.setId(today);
            session.setUserid(userid);
            sessionmapper.insertSession(session);
        }
        session.setMastered(session.getMastered() + mastered);
        session.setLearned(session.getLearned() + learned);
        session.setPracticed(session.getPracticed() + practiced);
        sessionmapper.updateSession(session);
    }

    @Transactional
    public void updateListWord(List<ListWordVO> words) {
        if (words == null || words.isEmpty()) return;

        String word = words.get(0).getWord();
        List<NamedList> removeNamedList = listmapper.listListByWord(word);
        List<NamedList> namedLists = listmapper.listLists();
        if (removeNamedList == null) removeNamedList = new ArrayList<>();
        if (namedLists == null) namedLists = new ArrayList<>();
        List nowList = removeNamedList.stream().map(NamedList::getTitle).collect(Collectors.toList());
        List newLists = namedLists.stream().map(NamedList::getTitle).collect(Collectors.toList());

        for (ListWordVO vo : words) {
            if (nowList.contains(vo.getList())) {
                removeNamedList = removeNamedList.stream()
                        .filter(list -> list.getTitle().equals(vo.getList()))
                        .collect(Collectors.toList());
            }

            if (!newLists.contains(vo.getList())) {
                NamedList namedList = new NamedList();
                namedList.setTitle(vo.getList());
                listmapper.createList(namedList);
            }

            NamedList namedList = listmapper.getListByTitle(vo.getList());
            listmapper.addListWord(namedList.getId(), vo.getWord());
        }

        for (NamedList namedList : removeNamedList) {
            listmapper.deleteListWord(namedList.getId(), word);
        }
    }

    @Transactional
    public void updateUserWord(String username, UserWord userWord) {
        AppUser user = usermapper.getUser(username);
        if (user == null) {
            LOGGER.log(Level.SEVERE, "unknow user access {0}", username);
            return;
        }
        userWord.setUserid(user.getId());

        String star = userWord.getStar();
        if (star == null || (!star.equals("N") && !star.equals("Y"))) {
            userWord.setStar("N");
        }
        Integer mastery = userWord.getMastery();
        if (mastery == null || mastery < Mastery.NEW.getLevel() || mastery > Mastery.MASTERED.getLevel()) {
            userWord.setMastery(Mastery.NEW.getLevel());
        }

        UserWord existing = userwordmapper.getUserWord(user.getId(), userWord.getWord());
        if (existing == null) {
            userwordmapper.insertUserWord(userWord);
        } else {
            userwordmapper.updateUserWord(userWord);
        }
    }

    private QuestionVO createRandomQuestion(String word) {
        int value = RANDOM.nextInt(10);
        if (value <= 5) return createClozeQuestion(word);
        else if (value <= 7) return createChooseWordQuestion(word);
        else return createChooseSentenseQuestion(word);
    }

    private QuestionVO createChooseWordQuestion(String word) {
        final QuestionVO vo = new QuestionVO();

        vo.setWord(word);
        vo.setQuestion(mapper.getFirstDefinition(word));

        List<String> alikes = othermapper.getLookAlike(word);
        Collections.shuffle(alikes);
        List<String> choises = new ArrayList<>(alikes.subList(0, 3));
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
        List<String> choises = new ArrayList<>(defintions.subList(0, 3));
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
        List<String> choises = new ArrayList<>(alikes.subList(0, 3));
        choises.add(word);
        Collections.shuffle(choises);
        vo.setChoises(choises);

        vo.setAnswer(choises.indexOf(word));
        return vo;
    }
}
