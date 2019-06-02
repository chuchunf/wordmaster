package me.wordmaster.model;

import java.sql.Timestamp;

/**
 * appuser table
 */
public class AppUser {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String status;
    private String role;
    private Timestamp created;
    private Timestamp lastLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreated() {
        if (created != null) {
            return new Timestamp(created.getTime());
        } else {
            return null;
        }
    }

    public void setCreated(Timestamp created) {
        if (created != null) {
            this.created = new Timestamp(created.getTime());
        }
    }

    public Timestamp getLastLogin() {
        if (lastLogin != null) {
            return new Timestamp(lastLogin.getTime());
        } else {
            return null;
        }
    }

    public void setLastLogin(Timestamp lastLogin) {
        if (lastLogin != null) {
            this.lastLogin = lastLogin;
        }
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", role='" + role + '\'' +
                ", created=" + created +
                ", lastLogin=" + lastLogin +
                '}';
    }
}
