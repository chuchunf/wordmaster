package me.wordmaster.service;

import me.wordmaster.dao.*;
import me.wordmaster.model.*;
import me.wordmaster.util.DateUtils;
import me.wordmaster.util.Mastery;
import me.wordmaster.vo.AnswerVO;
import me.wordmaster.vo.BookWordVO;
import me.wordmaster.vo.QuestionVO;
import me.wordmaster.vo.WordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class WordService {
    private static final Logger LOGGER = Logger.getLogger(WordService.class.getName());

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
    private BookMapper bookmapper;

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

    @Transactional
    public void updateRecord(String username, List<AnswerVO> answer) {
        AppUser user = usermapper.getUser(username);
        if (user == null) {
            LOGGER.severe("invalid user called updateRecord: [" + username + "]");
            return;
        }
        Long userid = user.getId();

        int learned = 0, practiced = 0, mastered = 0;
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
    public void updateBookWord(List<BookWordVO> words) {
        if (words == null || words.isEmpty()) return;

        String word = words.get(0).getWord();
        List<Book> removeList = bookmapper.listBookByWord(word);
        List<Book> books = bookmapper.listBooks();
        if (removeList == null) removeList = new ArrayList<>();
        if (books == null) books = new ArrayList<>();
        List<String> nowList = removeList.stream().map(Book::getTitle).collect(Collectors.toList());
        List<String> newBooks = books.stream().map(Book::getTitle).collect(Collectors.toList());

        for (BookWordVO vo : words) {
            if (nowList.contains(vo.getBook())) {
                removeList.remove(vo.getBook());
            }

            if (!newBooks.contains(vo.getBook())) {
                Book book = new Book();
                book.setTitle(vo.getBook());
                bookmapper.createBook(book);
            }

            Book book = bookmapper.getBookByTitle(vo.getBook());
            bookmapper.addBookWord(book.getId(), vo.getWord());
        }

        for (Book book : removeList) {
            bookmapper.deleteBookWord(book.getId(), word);
        }
    }

    @Transactional
    public void updateUserWord(String username, UserWord userWord) {
        AppUser user = usermapper.getUser(username);
        if (user == null) {
            LOGGER.severe("unknown user access !![" + username + "]");
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
