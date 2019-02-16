package me.wordmaster.vo;

import me.wordmaster.model.WordEntry;

import java.util.List;

public class WordVO {
    private String word;
    private List<WordEntry> entries;
    private List<String> synonyms;
    private List<String> acronyms;
    private List<String> derived; // derived from/to another word
    private List<String> alike;  // look alike words
    private List<String> related; // logic relation

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<WordEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<WordEntry> entries) {
        this.entries = entries;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getAcronyms() {
        return acronyms;
    }

    public void setAcronyms(List<String> acronyms) {
        this.acronyms = acronyms;
    }

    public List<String> getDerived() {
        return derived;
    }

    public void setDerived(List<String> derived) {
        this.derived = derived;
    }

    public List<String> getRelated() {
        return related;
    }

    public void setRelated(List<String> related) {
        this.related = related;
    }

    public List<String> getAlike() {
        return alike;
    }

    public void setAlike(List<String> alike) {
        this.alike = alike;
    }

    @Override
    public String toString() {
        return "WordVO{" +
                "word='" + word + '\'' +
                ", entries=" + entries +
                ", synonyms=" + synonyms +
                ", acronyms=" + acronyms +
                ", derived=" + derived +
                ", alike=" + alike +
                ", related=" + related +
                '}';
    }
}
