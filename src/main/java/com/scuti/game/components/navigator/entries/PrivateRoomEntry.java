package com.scuti.game.components.navigator.entries;

import com.scuti.game.components.navigator.entries.thumbnails.Thumbnail;

public class PrivateRoomEntry extends RoomEntry {
    private final boolean event;
    private final int state;
    private final boolean trading;
    private final int score;
    private final int category;
    private final String dateCreation;
    private final String tags;
    private final Thumbnail thumbnail;

    public PrivateRoomEntry(int roomId, String caption, String description, int usersMax, boolean event, int state, boolean trading, int score, int category, String dateCreation, String tags, Thumbnail thumbnail) {
        super(roomId, caption, description, usersMax);
        this.event = event;
        this.state = state;
        this.trading = trading;
        this.score = score;
        this.category = category;
        this.dateCreation = dateCreation;
        this.tags = tags;
        this.thumbnail = thumbnail;
    }

    public boolean isEvent() {
        return event;
    }

    public int getState() {
        return state;
    }

    public boolean isTrading() {
        return trading;
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

    public String getTags() {
        return tags;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }
}
