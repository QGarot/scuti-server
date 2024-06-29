package com.scuti.messages.incoming;

import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public abstract class MessageEvent {
    public abstract void handle(NettyConnection connection, NettyRequest clientMessage);
}
