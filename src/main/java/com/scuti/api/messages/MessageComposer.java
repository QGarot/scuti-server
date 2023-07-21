package com.scuti.api.messages;

import com.scuti.server.netty.streams.NettyResponse;

public abstract class MessageComposer {
    public abstract void compose(NettyResponse response);
    public abstract short getHeader();
}
