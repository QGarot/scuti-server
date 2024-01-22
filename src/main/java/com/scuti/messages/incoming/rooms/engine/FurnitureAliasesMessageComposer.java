package com.scuti.messages.incoming.rooms.engine;

import com.scuti.messages.outgoing.MessageComposer;

public class FurnitureAliasesMessageComposer extends MessageComposer {

    public FurnitureAliasesMessageComposer() {
        this.getResponse().setHeader(297);
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(0);
    }
}
