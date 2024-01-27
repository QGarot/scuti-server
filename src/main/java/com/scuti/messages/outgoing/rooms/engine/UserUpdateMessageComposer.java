package com.scuti.messages.outgoing.rooms.engine;

import com.scuti.game.rooms.entities.RoomUser;
import com.scuti.messages.outgoing.MessageComposer;

import java.util.HashMap;

public class UserUpdateMessageComposer extends MessageComposer {
    private final HashMap<Integer, RoomUser> roomUsers;

    public UserUpdateMessageComposer(HashMap<Integer, RoomUser> roomUsers) {
        this.getResponse().setHeader(34);
        this.roomUsers = roomUsers;
    }

    public HashMap<Integer, RoomUser> getRoomUsers() {
        return roomUsers;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(this.getRoomUsers().size());
        for (RoomUser roomUser: this.getRoomUsers().values()) {
            this.getResponse().appendInt32(roomUser.getVirtualId());
            this.getResponse().appendInt32(roomUser.getPosition().getX());
            this.getResponse().appendInt32(roomUser.getPosition().getY());
            this.getResponse().appendStringWithBreak(String.valueOf(roomUser.getPosition().getZ()));
            this.getResponse().appendInt32(roomUser.getRotationHead());
            this.getResponse().appendInt32(roomUser.getRotationBody());
            this.getResponse().appendString("/");
            this.getResponse().appendStringWithBreak("/");
        }
    }
}
