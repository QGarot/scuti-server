package com.scuti.game.components.navigator.entries;

public class RoomEntry {
    private final int roomId;
    private final String caption;
    private final String description;
    private final int usersMax;

    public RoomEntry(int roomId, String caption, String description, int usersMax) {
        this.roomId = roomId;
        this.caption = caption;
        this.description = description;
        this.usersMax = usersMax;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getCaption() {
        return caption;
    }

    public String getDescription() {
        return description;
    }

    public int getUsersMax() {
        return usersMax;
    }
}
