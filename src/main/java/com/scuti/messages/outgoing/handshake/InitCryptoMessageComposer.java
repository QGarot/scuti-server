package com.scuti.messages.outgoing.handshake;

import com.scuti.messages.outgoing.MessageComposer;

public class InitCryptoMessageComposer extends MessageComposer {
    public InitCryptoMessageComposer() {
        this.getResponse().setHeader(277);
    }

    @Override
    public void compose() {
        this.getResponse().appendString("02d6b0969196802df2");
        this.getResponse().appendBoolean(false);
    }
}
