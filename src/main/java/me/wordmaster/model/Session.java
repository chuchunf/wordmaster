package me.wordmaster.model;

public class Session {
    private String id;
    private Integer userid;
    private Integer practiced;
    private Integer learned;
    private Integer mastered;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
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
