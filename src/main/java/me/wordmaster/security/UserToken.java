package me.wordmaster.security;

import java.util.Date;

public class UserToken {
    private final String username;
    private final Date issuedAt;

    public UserToken(String username, Date issuedAt) {
        this.username = username;
        this.issuedAt = issuedAt;
    }

    public String getUsername() {
        return username;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "username='" + username + '\'' +
                ", issuedAt=" + issuedAt +
                '}';
    }
}
