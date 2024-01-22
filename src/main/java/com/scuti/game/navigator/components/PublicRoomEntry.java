package com.scuti.game.navigator.components;

public class PublicRoomEntry {
    // TODO: define and use RoomDetails object
    private int id;
    private String caption;
    private String description;
    private String image;
    private int recommended;
    private int order;
    private int roomId;

    public PublicRoomEntry(int id, String caption, String description, String image, int recommended, int order, int roomId) {
        this.id = id;
        this.caption = caption;
        this.description = description;
        this.image = image;
        this.recommended = recommended;
        this.order = order;
        this.roomId = roomId;
    }

    public int getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getRecommended() {
        return recommended;
    }

    public int getOrder() {
        return order;
    }

    public int getRoomId() {
        return roomId;
    }
}
