package com.scuti.game.users.friends;

public class Buddy {
    private int id;
    private String username;
    private int gender;
    private boolean online;
    private boolean followingAllowed;
    private String figure;
    private int categoryId;
    private String motto;
    private String lastLogin;
    private String facebookId;

    public Buddy(int id, String username, int gender, boolean online, boolean followingAllowed, String figure, int categoryId, String motto, String lastLogin, String facebookId) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.online = online;
        this.followingAllowed = followingAllowed;
        this.figure = figure;
        this.categoryId = categoryId;
        this.motto = motto;
        this.lastLogin = lastLogin;
        this.facebookId = facebookId;
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

    public int getCategoryId() {
        return categoryId;
    }

    public String getMotto() {
        return motto;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public String getFacebookId() {
        return facebookId;
    }
}
