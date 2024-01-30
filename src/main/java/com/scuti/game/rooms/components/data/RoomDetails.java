package com.scuti.game.rooms.components.data;

import java.util.Objects;

public class RoomDetails {
    private int id;
    private String type;
    private String caption;
    private String ownerName;
    private String description;
    private int category;
    private String state;
    private int usersNow;
    private int usersMax;
    private String modelName;
    private int score;
    private String tags;
    private String password;
    private String wallpaper;
    private String floor;
    private String landscape;
    private boolean tradingAllowed;
    private String dateCreation;
    private boolean petsAllowed;
    private boolean displayRoomEntryAd;

    public RoomDetails() {
        // default values
        this.type = "private";
        this.description = "";
        this.category = 1;
        this.state = "open";
        this.usersNow = 0;
        this.usersMax = 25;
        this.score = 0;
        this.tags = "";
        this.password = "";
        this.wallpaper = "0.0";
        this.floor = "0.0";
        this.landscape = "0.0";
        this.tradingAllowed = true;
        this.dateCreation = "";
        this.petsAllowed = true;
        this.displayRoomEntryAd = true;
    }

    public void dispose() {
        this.type = null;
        this.description = null;
        this.category = 0;
        this.state = null;
        this.usersNow = 0;
        this.usersMax = 0;
        this.score = 0;
        this.tags = null;
        this.password = null;
        this.wallpaper = null;
        this.floor = null;
        this.landscape = null;
        this.tradingAllowed = true;
        this.dateCreation = null;
        this.petsAllowed = false;
        this.displayRoomEntryAd = false;
    }

    public void fill(int id, String type, String caption, String ownerName, String description, int category, String state, int usersNow, int usersMax, String modelName, int score, String tags, String password, String wallpaper, String floor, String landscape, boolean tradingAllowed, boolean petsAllowed, boolean displayRoomEntryAd, String dateCreation) {
        this.id = id;
        this.type = type;
        this.caption = caption;
        this.ownerName = ownerName;
        this.description = description;
        this.category = category;
        this.state = state;
        this.usersNow = usersNow;
        this.usersMax = usersMax;
        this.modelName = modelName;
        this.score = score;
        this.tags = tags;
        this.password = password;
        this.wallpaper = wallpaper;
        this.floor = floor;
        this.landscape = landscape;
        this.tradingAllowed = tradingAllowed;
        this.dateCreation = dateCreation;
        this.petsAllowed = petsAllowed;
        this.displayRoomEntryAd = displayRoomEntryAd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getState() {
        if (Objects.equals(this.state, "open")) {
            return 0;
        } else if (Objects.equals(this.state, "locked")) {
            return 1;
        } else if (Objects.equals(this.state, "password")) {
            return 2;
        } else {
            return 0;
        }
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getUsersNow() {
        return usersNow;
    }

    public void setUsersNow(int usersNow) {
        this.usersNow = usersNow;
    }

    public int getUsersMax() {
        return usersMax;
    }

    public void setUsersMax(int usersMax) {
        this.usersMax = usersMax;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(String wallpaper) {
        this.wallpaper = wallpaper;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getLandscape() {
        return landscape;
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }

    public boolean isTradingAllowed() {
        return tradingAllowed;
    }

    public void setTradingAllowed(boolean tradingAllowed) {
        this.tradingAllowed = tradingAllowed;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public boolean arePetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public boolean isDisplayRoomEntryAd() {
        return displayRoomEntryAd;
    }

    public void setDisplayRoomEntryAd(boolean displayRoomEntryAd) {
        this.displayRoomEntryAd = displayRoomEntryAd;
    }
}
