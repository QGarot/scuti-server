package com.scuti.game.rooms.entities;

public class RoomUser extends RoomEntity {
    private int userId;
    private int virtualId;
    private int rotationHead;
    private int rotationBody;

    public RoomUser(int userId, int x, int y, int z, int rotation) {
        this.userId = userId;
        this.rotationBody = rotation;
        this.rotationHead = rotation;

        this.getPosition().setX(x);
        this.getPosition().setY(y);
        this.getPosition().setZ(z);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void kick() {

    }

    @Override
    public void walk() {

    }

    @Override
    public void talk() {

    }

    public int getUserId() {
        return userId;
    }

    public int getVirtualId() {
        return virtualId;
    }

    public int getRotationHead() {
        return rotationHead;
    }

    public int getRotationBody() {
        return rotationBody;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setVirtualId(int virtualId) {
        this.virtualId = virtualId;
    }

    public void setRotationHead(int rotationHead) {
        this.rotationHead = rotationHead;
    }

    public void setRotationBody(int rotationBody) {
        this.rotationBody = rotationBody;
    }
}
