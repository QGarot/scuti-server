package com.scuti.game.components.navigator.entries.thumbnails;

public class Thumbnail {
    private final int backgroundImage;
    private final int foregroundImage;
    private final int itemCount;
    private final int itemPosition;
    private final int itemImage;

    public Thumbnail(int backgroundImage, int foregroundImage, int itemCount, int itemPosition, int itemImage) {
        this.backgroundImage = backgroundImage;
        this.foregroundImage = foregroundImage;
        this.itemCount = itemCount;
        this.itemPosition = itemPosition;
        this.itemImage = itemImage;
    }

    public int getBackgroundImage() {
        return backgroundImage;
    }

    public int getForegroundImage() {
        return foregroundImage;
    }

    public int getItemCount() {
        return itemCount;
    }

    public int getItemPosition() {
        return itemPosition;
    }

    public int getItemImage() {
        return itemImage;
    }
}
