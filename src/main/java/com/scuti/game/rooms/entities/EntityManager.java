package com.scuti.game.rooms.entities;

import java.util.HashMap;

public class EntityManager {
    private final HashMap<Integer, RoomUser> roomUsers;

    public EntityManager() {
        this.roomUsers = new HashMap<>();
    }

    public HashMap<Integer, RoomUser> getRoomUsers() {
        return roomUsers;
    }

    public int countUsers() {
        return this.getRoomUsers().size();
    }

    public RoomUser getRoomUserById(int userId) {
        return this.getRoomUsers().get(userId);
    }

    public void disposeRoomUser(int userId) {
        RoomUser roomUser = this.getRoomUsers().remove(userId);
        roomUser.dispose();
    }

    public void addRoomUser(RoomUser roomUser) {
        roomUser.setVirtualId(this.countUsers() + 1);
        this.getRoomUsers().put(roomUser.getUserId(), roomUser);
    }
}
