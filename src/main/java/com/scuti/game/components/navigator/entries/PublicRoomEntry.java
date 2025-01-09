package com.scuti.game.components.navigator.entries;

public class PublicRoomEntry extends RoomEntry {
    private final int id;
    private final String image;
    private final boolean recommanded;

    public PublicRoomEntry(int roomId, String caption, String description, int usersMax, int id, String image, boolean recommanded) {
        super(roomId, caption, description, usersMax);
        this.id = id;
        this.image = image;
        this.recommanded = recommanded;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public boolean isRecommanded() {
        return recommanded;
    }
}
