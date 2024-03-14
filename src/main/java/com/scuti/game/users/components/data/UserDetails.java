package com.scuti.game.users.components.data;

public class UserDetails {
    private int id;
    private String username;
    private String email;
    private String figure;
    private String motto;
    private String sex;
    private String ssoTicket;
    private int rank;
    private int credits;
    private int pixels;
    private int shells;
    private int respect;
    private int dailyRespectPoints;
    private boolean online;

    public UserDetails(int id, String username, String email, String figure, String motto, String sex, String ssoTicket, int rank, int credits, int pixels, int shells, boolean online) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.figure = figure;
        this.motto = motto;
        this.sex = sex;
        this.ssoTicket = ssoTicket;
        this.rank = rank;
        this.credits = credits;
        this.pixels = pixels;
        this.shells = shells;
        this.respect = 0;
        this.dailyRespectPoints = 3;
        this.online = online;
    }

    public void dispose() {
        this.id = 0;
        this.username = null;
        this.email = null;
        this.figure = null;
        this.motto = null;
        this.sex = null;
        this.ssoTicket = null;
        this.rank = 0;
        this.credits = 0;
        this.pixels = 0;
        this.shells = 0;
        this.respect = 0;
        this.dailyRespectPoints = 0;
        this.online = false;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean bool) {
        this.online = bool;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setSsoTicket(String ssoTicket) {
        this.ssoTicket = ssoTicket;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void addCredits(int credits) {
        this.credits = this.credits + credits;
    }

    public void addPixels(int pixels) {
        this.pixels = this.pixels + pixels;
    }

    public void addShells(int shells) {
        this.shells = this.shells + shells;
    }

    public void setRespect(int respect) {
        this.respect = respect;
    }

    public void setDailyRespectPoints(int dailyRespectPoints) {
        this.dailyRespectPoints = dailyRespectPoints;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFigure() {
        return figure;
    }

    public String getMotto() {
        return motto;
    }

    public String getSex() {
        return sex;
    }

    public String getSsoTicket() {
        return ssoTicket;
    }

    public int getRank() {
        return rank;
    }

    public int getCredits() {
        return credits;
    }

    public int getPixels() {
        return pixels;
    }

    public int getShells() {
        return shells;
    }

    public int getRespect() {
        return respect;
    }

    public int getDailyRespectPoints() {
        return dailyRespectPoints;
    }
}
