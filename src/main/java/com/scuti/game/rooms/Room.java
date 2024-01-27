package com.scuti.game.rooms;

import com.scuti.game.rooms.components.data.RoomDetails;
import com.scuti.game.navigator.components.Thumbnail;
import com.scuti.game.rooms.entities.EntityManager;

public class Room {
    private final RoomDetails details;
    private final Thumbnail thumbnail;
    private final EntityManager entityManager;

    public Room() {
        this.details = new RoomDetails();
        this.thumbnail = new Thumbnail();
        this.entityManager = new EntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public RoomDetails getDetails() {
        return details;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }
}
