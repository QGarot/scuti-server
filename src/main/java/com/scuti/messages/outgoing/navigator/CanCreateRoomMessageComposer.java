package com.scuti.messages.outgoing.navigator;

import com.scuti.messages.outgoing.MessageComposer;

public class CanCreateRoomMessageComposer extends MessageComposer {
    private int resultCode;
    private int roomLimit;

    public CanCreateRoomMessageComposer(int resultCode, int roomLimit) {
        this.getResponse().setHeader(512);
        this.resultCode = resultCode;
        this.roomLimit = roomLimit;
    }

    public int getResultCode() {
        return resultCode;
    }

    public int getRoomLimit() {
        return roomLimit;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(this.getResultCode());
        this.getResponse().appendInt32(this.getRoomLimit());
    }
}
