package com.scuti.messages.outgoing.handshake;

import com.scuti.messages.outgoing.MessageComposer;

public class SessionParamsMessageComposer extends MessageComposer {

    public SessionParamsMessageComposer() {
        this.getResponse().setHeader(257);
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(0);
    }
}
