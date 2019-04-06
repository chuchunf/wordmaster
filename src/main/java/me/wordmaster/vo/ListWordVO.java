package me.wordmaster.vo;

public class ListWordVO {
    private String word;
    private String list;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ListWordVO{" +
                "word='" + word + '\'' +
                ", list='" + list + '\'' +
                '}';
    }
}
