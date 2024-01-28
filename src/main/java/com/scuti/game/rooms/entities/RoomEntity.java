package com.scuti.game.rooms.entities;

import com.scuti.game.rooms.mapping.Position;

public abstract class RoomEntity {
    private Position position = new Position();
    private boolean isWalking;
    private int danceId;
    private int effectId;
    private int carryId;
    private boolean isSleeping;

    public abstract void kick();
    public abstract void walk();
    public abstract void talk();

    public void dispose() {
        this.setPosition(null);
        this.setWalking(false);
        this.setDanceId(0);
        this.setEffectId(0);
        this.setCarryId(0);
        this.setSleeping(false);
    }

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

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setWalking(boolean walking) {
        isWalking = walking;
    }

    public void setDanceId(int danceId) {
        this.danceId = danceId;
    }

    public void setEffectId(int effectId) {
        this.effectId = effectId;
    }

    public void setCarryId(int carryId) {
        this.carryId = carryId;
    }

    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }
}
