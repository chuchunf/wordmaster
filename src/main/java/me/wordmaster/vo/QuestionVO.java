package me.wordmaster.vo;

import java.util.Arrays;

public class QuestionVO {
    private String word;
    private String question;
    private String[] choises;
    private int answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String[] getChoises() {
        return choises;
    }

    public void setChoises(String[] choises) {
        this.choises = choises;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "word ='" + word + '\'' +
                ", question='" + question + '\'' +
                ", choises=" + Arrays.toString(choises) +
                ", answer=" + answer +
                '}';
    }
}
