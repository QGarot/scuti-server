package com.scuti.messages.outgoing.rooms.session;

import com.scuti.messages.outgoing.MessageComposer;

public class RoomForwardMessageComposer extends MessageComposer {
    private final boolean isPublic;
    private final int roomId;

    public RoomForwardMessageComposer(boolean isPublic, int roomId) {
        this.getResponse().setHeader(286);
        this.isPublic = isPublic;
        this.roomId = roomId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public int getRoomId() {
        return roomId;
    }

    @Override
    public void compose() {
        this.getResponse().appendBoolean(this.isPublic());
        this.getResponse().appendInt32(this.getRoomId());
    }
}
