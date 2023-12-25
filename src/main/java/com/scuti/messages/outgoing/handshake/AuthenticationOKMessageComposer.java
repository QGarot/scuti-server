package com.scuti.messages.outgoing.handshake;

import com.scuti.messages.outgoing.MessageComposer;

public class AuthenticationOKMessageComposer extends MessageComposer {
    public AuthenticationOKMessageComposer() {
        this.getResponse().setHeader(3);
    }

    @Override
    public void compose() {

    }
}
