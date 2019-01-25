package me.wordmaster.vo;

import me.wordmaster.model.AppUser;
import me.wordmaster.model.Badge;

import java.util.List;

/**
 * AppUser + UserBadge
 */
public class UserVO {
    private AppUser user;
    private List<Badge> badges;
    private String jwt;

    public UserVO() {
    }

    public UserVO(AppUser user, List<Badge> badges, String jwt) {
        this.user = user;
        this.badges = badges;
        this.jwt = jwt;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "user=" + user +
                ", badges=" + badges +
                ", jwt='" + jwt + '\'' +
                '}';
    }
}
