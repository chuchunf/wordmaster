package me.wordmaster.model;

public class UserWord {
    private Long userid;
    private String word;
    private String star;
    private Integer attempt = 0;
    private Integer mastery = 0;
    private String status;
    private String created;
    private String updated;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

    public Integer getMastery() {
        return mastery;
    }

    public void setMastery(Integer mastery) {
        this.mastery = mastery;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "UserWord{" +
                "userid=" + userid +
                ", word='" + word + '\'' +
                ", star='" + star + '\'' +
                ", attempt=" + attempt +
                ", mastery=" + mastery +
                ", status='" + status + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
