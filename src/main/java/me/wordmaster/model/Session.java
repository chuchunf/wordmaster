package me.wordmaster.model;

public class Session {
    private String id;
    private Long userid = 0L;
    private Integer practiced = 0;
    private Integer learned = 0;
    private Integer mastered = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getPracticed() {
        return practiced;
    }

    public void setPracticed(Integer practiced) {
        this.practiced = practiced;
    }

    public Integer getLearned() {
        return learned;
    }

    public void setLearned(Integer learned) {
        this.learned = learned;
    }

    public Integer getMastered() {
        return mastered;
    }

    public void setMastered(Integer mastered) {
        this.mastered = mastered;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", userid=" + userid +
                ", practiced=" + practiced +
                ", learned=" + learned +
                ", mastered=" + mastered +
                '}';
    }
}
