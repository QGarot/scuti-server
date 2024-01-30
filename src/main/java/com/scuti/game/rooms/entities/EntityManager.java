package com.scuti.game.rooms.entities;

import java.util.HashMap;

public class EntityManager {
    private HashMap<Integer, RoomUser> roomUsers;

    public EntityManager() {
        this.roomUsers = new HashMap<>();
    }

    public void dispose() {
        for (int userId: this.getRoomUsers().keySet()) {
            this.disposeRoomUser(userId);
        }
        this.roomUsers = null;
    }

    public HashMap<Integer, RoomUser> getRoomUsers() {
        return roomUsers;
    }

    public int countUsers() {
        return this.getRoomUsers().size();
    }

    public boolean isInRoom(int userId) {
        return this.getRoomUsers().containsKey(userId);
    }

    public RoomUser getRoomUserById(int userId) {
        return this.getRoomUsers().get(userId);
    }

    public void disposeRoomUser(int userId) {
        this.getRoomUsers().remove(userId).dispose();
    }

    public void addRoomUser(RoomUser roomUser) {
        roomUser.setVirtualId(this.countUsers() + 1);
        this.getRoomUsers().put(roomUser.getUserId(), roomUser);
    }
}
