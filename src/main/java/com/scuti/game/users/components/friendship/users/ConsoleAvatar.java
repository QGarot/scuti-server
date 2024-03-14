package com.scuti.game.users.components.friendship.users;

public class ConsoleAvatar {
    private final int id;
    private final String username;
    private final int gender;
    private final boolean online;
    private final boolean followingAllowed;
    private final String figure;
    private final String motto;
    private final String lastLogin;

    public ConsoleAvatar(int id, String username, int gender, boolean online, boolean followingAllowed, String figure, String motto, String lastLogin) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.online = online;
        this.followingAllowed = followingAllowed;
        this.figure = figure;
        this.motto = motto;
        this.lastLogin = lastLogin;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getGender() {
        return gender;
    }

    public boolean isOnline() {
        return online;
    }

    public boolean isFollowingAllowed() {
        return followingAllowed;
    }

    public String getFigure() {
        return figure;
    }

    public String getMotto() {
        return motto;
    }

    public String getLastLogin() {
        return lastLogin;
    }
}
