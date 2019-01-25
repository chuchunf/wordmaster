package me.wordmaster.model;

public class Badge {
    private Long id;
    private Long level;
    private String type;
    private String icon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Badge{" +
                "id=" + id +
                ", level=" + level +
                ", type='" + type + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
