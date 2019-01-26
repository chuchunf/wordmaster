package me.wordmaster.model;

import java.util.List;

public class WordEntry {
    private String word;
    private String seqno;
    private String category;
    private String origin;
    private String ftype;
    private String fvalue;
    private String stype;
    private String svalue;
    private List<WordSense> senses;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getFvalue() {
        return fvalue;
    }

    public void setFvalue(String fvalue) {
        this.fvalue = fvalue;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getSvalue() {
        return svalue;
    }

    public void setSvalue(String svalue) {
        this.svalue = svalue;
    }

    public List<WordSense> getSenses() {
        return senses;
    }

    public void setSenses(List<WordSense> senses) {
        this.senses = senses;
    }

    @Override
    public String toString() {
        return "WordEntry{" +
                "word='" + word + '\'' +
                ", seqno='" + seqno + '\'' +
                ", category='" + category + '\'' +
                ", origin='" + origin + '\'' +
                ", ftype='" + ftype + '\'' +
                ", fvalue='" + fvalue + '\'' +
                ", stype='" + stype + '\'' +
                ", svalue='" + svalue + '\'' +
                ", senses=" + senses +
                '}';
    }
}
