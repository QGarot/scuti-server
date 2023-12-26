package com.scuti.messages.outgoing.handshake;

import com.scuti.messages.outgoing.MessageComposer;

public class DisconnectReasonMessageComposer extends MessageComposer {
    private int reason;
    public DisconnectReasonMessageComposer(int reason) {
        this.getResponse().setHeader(287);
        this.reason = reason;
        // Banned : 10
        // Concurrent login : 2
        // Logout : 1
    }

    public int getReason() {
        return this.reason;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(this.getReason());
    }
}
