package com.scuti.messages.outgoing;

import com.scuti.server.netty.streams.NettyResponse;

public abstract class MessageComposer {
    private NettyResponse response = new NettyResponse();
    public abstract void compose();
    public NettyResponse getResponse() {
        return this.response;
    }
}