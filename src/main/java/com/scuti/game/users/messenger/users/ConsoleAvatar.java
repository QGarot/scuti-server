package com.scuti.game.users.messenger.users;

public class ConsoleAvatar {
    private int id;
    private String username;
    private int gender;
    private boolean online;
    private boolean followingAllowed;
    private String figure;
    private String motto;
    private String lastLogin;

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
