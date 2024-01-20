package com.scuti.game.rooms;

import com.scuti.game.rooms.components.data.RoomDetails;
import com.scuti.game.rooms.mapping.RoomModel;

public class Room {
    private final RoomDetails details;

    public Room() {
        this.details = new RoomDetails();
    }

    public RoomDetails getDetails() {
        return details;
    }
}
