package me.wordmaster.model;

public class TopUser {
    private String username;
    private Integer count;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TopUser{" +
                "username='" + username + '\'' +
                ", count=" + count +
                '}';
    }
}
