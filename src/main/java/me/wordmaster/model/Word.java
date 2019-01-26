package me.wordmaster.model;

public class Word {
    private String word;
    private String category;
    private String definition;
    private String relation; // synonym, acronym, etc

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", category='" + category + '\'' +
                ", definition='" + definition + '\'' +
                ", relation='" + relation + "\'" +
                '}';
    }
}
