package com.scuti.game.rooms;

import com.scuti.game.rooms.components.data.RoomDetails;
import com.scuti.game.navigator.components.Thumbnail;
import com.scuti.game.rooms.entities.EntityManager;

public class Room {
    private RoomDetails details;
    private Thumbnail thumbnail;
    private EntityManager entityManager;

    public Room() {
        this.details = new RoomDetails();
        this.thumbnail = new Thumbnail();
        this.entityManager = new EntityManager();
    }

    public void dispose() {
        this.getDetails().dispose();
        this.details = null;

        this.getThumbnail().dispose();
        this.thumbnail = null;

        this.getEntityManager().dispose();
        this.entityManager = null;
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
