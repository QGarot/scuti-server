package com.scuti.game.rooms.entities;

import com.scuti.game.rooms.mapping.Position;

public abstract class RoomEntity {
    private Position position = new Position();
    private boolean isWalking;
    private int danceId;
    private int effectId;
    private int carryId;
    private boolean isSleeping;

    public abstract void dispose();
    public abstract void kick();
    public abstract void walk();
    public abstract void talk();

    public Position getPosition() {
        return position;
    }

    public boolean isWalking() {
        return isWalking;
    }

    public int getDanceId() {
        return danceId;
    }

    public int getEffectId() {
        return effectId;
    }

    public int getCarryId() {
        return carryId;
    }

    public boolean isSleeping() {
        return isSleeping;
    }
}
