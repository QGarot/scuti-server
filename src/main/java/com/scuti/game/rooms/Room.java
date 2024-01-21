package com.scuti.game.rooms;

import com.scuti.game.rooms.components.data.RoomDetails;
import com.scuti.game.rooms.components.navigator.Thumbnail;

public class Room {
    private final RoomDetails details;
    private final Thumbnail thumbnail;

    public Room() {
        this.details = new RoomDetails();
        this.thumbnail = new Thumbnail();
    }

    public RoomDetails getDetails() {
        return details;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }
}
