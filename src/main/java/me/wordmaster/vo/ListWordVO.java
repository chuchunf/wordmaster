package me.wordmaster.vo;

public class BookWordVO {
    private String word;
    private String book;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookWordVO{" +
                "word='" + word + '\'' +
                ", book='" + book + '\'' +
                '}';
    }
}
