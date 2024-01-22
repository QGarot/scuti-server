package com.scuti.messages.outgoing.rooms.session;

import com.scuti.messages.outgoing.MessageComposer;

public class RoomReadyMessageComposer extends MessageComposer {
    private final int RoomId;
    private final String roomModel;

    public RoomReadyMessageComposer(int roomId, String roomModel) {
        this.getResponse().setHeader(69);
        RoomId = roomId;
        this.roomModel = roomModel;
    }

    public int getRoomId() {
        return RoomId;
    }

    public String getRoomModel() {
        return roomModel;
    }

    @Override
    public void compose() {
        this.getResponse().appendStringWithBreak(this.getRoomModel());
        this.getResponse().appendInt32(this.getRoomId());
    }
}
