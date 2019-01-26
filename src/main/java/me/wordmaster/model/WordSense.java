package me.wordmaster.model;

public class WordSense {
    private String id;
    private String word;
    private String seqno;
    private String thesaurus;
    private String domain;
    private String definition;
    private String sample;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getThesaurus() {
        return thesaurus;
    }

    public void setThesaurus(String thesaurus) {
        this.thesaurus = thesaurus;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    @Override
    public String toString() {
        return "WordSense{" +
                "id='" + id + '\'' +
                ", word='" + word + '\'' +
                ", seqno='" + seqno + '\'' +
                ", thesaurus='" + thesaurus + '\'' +
                ", domain='" + domain + '\'' +
                ", definition='" + definition + '\'' +
                ", sample='" + sample + '\'' +
                '}';
    }
}
