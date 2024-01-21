package com.scuti.game.rooms.components.navigator;

public class RoomNavigator {
    private int id;
    private String caption;
    private String ownerName;
    private String state; // open (0), locked (1), password (2)
    private int usersNow;
    private int capacity;
    private String description;
    private boolean tradingAllowed;
    private int score;
    private int category;
    private String dateCreation;
    private String[] tags;
    private int backgroundImageIcon;
    private int foregroundImageIcon;
    private int itemCount;
    private int itemPosition;
    private int itemImageId;
    private boolean petsAllowed;
    private boolean displayRoomEntryAd;

    public RoomNavigator(int id, String caption, String ownerName, String state, int usersNow, int capacity, String description, boolean tradingAllowed, int score, int category, String dateCreation, String[] tags, int backgroundImageIcon, int foregroundImageIcon, int itemCount, int itemPosition, int itemImageId, boolean petsAllowed, boolean displayRoomEntryAd) {
        this.id = id;
        this.caption = caption;
        this.ownerName = ownerName;
        this.state = state;
        this.usersNow = usersNow;
        this.capacity = capacity;
        this.description = description;
        this.tradingAllowed = tradingAllowed;
        this.score = score;
        this.category = category;
        this.dateCreation = dateCreation;
        this.tags = tags;
        this.backgroundImageIcon = backgroundImageIcon;
        this.foregroundImageIcon = foregroundImageIcon;
        this.itemCount = itemCount;
        this.itemPosition = itemPosition;
        this.itemImageId = itemImageId;
        this.petsAllowed = petsAllowed;
        this.displayRoomEntryAd = displayRoomEntryAd;
    }

    public int getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getState() {
        if (this.state == "open") {
            return 0;
        } else if (this.state == "locked") {
            return 1;            
        } else if (this.state == "password") {
            return 2;
        } else {
            return 0;
        }
    }

    public int getUsersNow() {
        return usersNow;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public boolean isTradingAllowed() {
        return tradingAllowed;
    }

    public int getScore() {
        return score;
    }

    public int getCategory() {
        return category;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public String[] getTags() {
        return tags;
    }

    public int getBackgroundImageIcon() {
        return backgroundImageIcon;
    }

    public int getForegroundImageIcon() {
        return foregroundImageIcon;
    }

    public int getItemCount() {
        return itemCount;
    }

    public int getItemPosition() {
        return itemPosition;
    }

    public int getItemImageId() {
        return itemImageId;
    }

    public boolean arePetsAllowed() {
        return petsAllowed;
    }

    public boolean displayRoomEntryAd() {
        return displayRoomEntryAd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /*public void setState(int state) {
        this.state = state;
    }*/

    public void setUsersNow(int usersNow) {
        this.usersNow = usersNow;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTradingAllowed(boolean tradingAllowed) {
        this.tradingAllowed = tradingAllowed;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setBackgroundImageIcon(int backgroundImageIcon) {
        this.backgroundImageIcon = backgroundImageIcon;
    }

    public void setForegroundImageIcon(int foregroundImageIcon) {
        this.foregroundImageIcon = foregroundImageIcon;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setItemPosition(int itemPosition) {
        this.itemPosition = itemPosition;
    }

    public void setItemImageId(int itemImageId) {
        this.itemImageId = itemImageId;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public void setDisplayRoomEntryAd(boolean displayRoomEntryAd) {
        this.displayRoomEntryAd = displayRoomEntryAd;
    }
}
