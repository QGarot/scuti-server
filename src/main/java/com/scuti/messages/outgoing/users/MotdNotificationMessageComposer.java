package com.scuti.messages.outgoing.users;

import com.scuti.messages.outgoing.MessageComposer;
import com.scuti.server.netty.streams.NettyResponse;

public class MotdNotificationMessageComposer extends MessageComposer {
    public MotdNotificationMessageComposer() {
        this.getResponse().setHeader(810);
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(5);
        this.getResponse().appendStringWithBreak("Welcome to ScutiHotel enft •");
        this.getResponse().appendStringWithBreak("");
        this.getResponse().appendStringWithBreak("");
        this.getResponse().appendStringWithBreak("");
        this.getResponse().appendStringWithBreak("");
    }
}
