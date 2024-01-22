package com.scuti.messages.outgoing.rooms.session;

import com.scuti.messages.outgoing.MessageComposer;

public class OpenConnectionMessageComposer extends MessageComposer {
    private final int roomId;
    private final int roomCategoryId;

    public OpenConnectionMessageComposer(int roomId, int roomCategoryId) {
        this.getResponse().setHeader(19);
        this.roomId = roomId;
        this.roomCategoryId = roomCategoryId;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getRoomCategoryId() {
        return roomCategoryId;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(this.getRoomId());
        this.getResponse().appendInt32(this.getRoomCategoryId());
    }
}
