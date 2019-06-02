package me.wordmaster.security;

import java.util.Date;

public class UserToken {
    private final String username;
    private final Date issuedAt;

    public UserToken(String username, Date issuedAt) {
        this.username = username;
        if (issuedAt != null) {
            this.issuedAt = new Date(issuedAt.getTime());
        } else {
            this.issuedAt = null;
        }
    }

    public String getUsername() {
        return username;
    }

    public Date getIssuedAt() {
        if (issuedAt != null) {
            return new Date(issuedAt.getTime());
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "username='" + username + '\'' +
                ", issuedAt=" + issuedAt +
                '}';
    }
}
