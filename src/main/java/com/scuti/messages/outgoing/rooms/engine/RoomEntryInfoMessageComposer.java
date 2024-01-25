package com.scuti.messages.outgoing.rooms.engine;

import com.scuti.messages.outgoing.MessageComposer;

public class RoomEntryInfoMessageComposer extends MessageComposer {
    private final boolean isPrivate;
    private final int roomId;
    private final boolean hasRights;

    public RoomEntryInfoMessageComposer(boolean isPrivate, int roomId, boolean hasRights) {
        this.getResponse().setHeader(471);
        this.isPrivate = isPrivate;
        this.roomId = roomId;
        this.hasRights = hasRights;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public int getRoomId() {
        return roomId;
    }

    public boolean hasRights() {
        return hasRights;
    }

    @Override
    public void compose() {
        this.getResponse().appendBoolean(this.isPrivate());
        this.getResponse().appendInt32(this.getRoomId());
        this.getResponse().appendBoolean(this.hasRights());
    }
}
