package com.scuti.game.rooms.components.navigator;

public class Thumbnail {
    private int backgroundImageIcon;
    private int foregroundImageIcon;
    private int itemCount;
    private int itemPosition;
    private int itemImageId;

    public Thumbnail() {
        // default values
        this.backgroundImageIcon = 1;
        this.foregroundImageIcon = 1;
        this.itemCount = 1;
        this.itemPosition = 1;
        this.itemImageId = 1;
    }

    public void fill(int backgroundImageIcon, int foregroundImageIcon, int itemCount, int itemPosition, int itemImageId) {
        this.backgroundImageIcon = backgroundImageIcon;
        this.foregroundImageIcon = foregroundImageIcon;
        this.itemCount = itemCount;
        this.itemPosition = itemPosition;
        this.itemImageId = itemImageId;
    }

    public int getBackgroundImageIcon() {
        return backgroundImageIcon;
    }

    public void setBackgroundImageIcon(int backgroundImageIcon) {
        this.backgroundImageIcon = backgroundImageIcon;
    }

    public int getForegroundImageIcon() {
        return foregroundImageIcon;
    }

    public void setForegroundImageIcon(int foregroundImageIcon) {
        this.foregroundImageIcon = foregroundImageIcon;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getItemPosition() {
        return itemPosition;
    }

    public void setItemPosition(int itemPosition) {
        this.itemPosition = itemPosition;
    }

    public int getItemImageId() {
        return itemImageId;
    }

    public void setItemImageId(int itemImageId) {
        this.itemImageId = itemImageId;
    }
}
