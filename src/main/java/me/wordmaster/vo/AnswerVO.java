package me.wordmaster.vo;

public class AnswerVO {
    private String word;
    private Boolean result;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AnswerVO{" +
                "word='" + word + '\'' +
                ", result=" + result +
                '}';
    }
}
