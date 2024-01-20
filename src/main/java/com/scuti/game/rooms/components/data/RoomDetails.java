package com.scuti.game.rooms.components.data;

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
    private int iconBackground;
    private int iconForeground;
    private String iconItems;
    private String password;
    private String wallpaper;
    private String floor;
    private String landscape;

    public RoomDetails(int id, String type, String caption, String ownerName, String description, int category, String state, int usersNow, int usersMax, String modelName, int score, String tags, int iconBackground, int iconForeground, String iconItems, String password, String wallpaper, String floor, String landscape) {
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
        this.iconBackground = iconBackground;
        this.iconForeground = iconForeground;
        this.iconItems = iconItems;
        this.password = password;
        this.wallpaper = wallpaper;
        this.floor = floor;
        this.landscape = landscape;
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

    public String getState() {
        return state;
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

    public int getIconBackground() {
        return iconBackground;
    }

    public void setIconBackground(int iconBackground) {
        this.iconBackground = iconBackground;
    }

    public int getIconForeground() {
        return iconForeground;
    }

    public void setIconForeground(int iconForeground) {
        this.iconForeground = iconForeground;
    }

    public String getIconItems() {
        return iconItems;
    }

    public void setIconItems(String iconItems) {
        this.iconItems = iconItems;
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
}
