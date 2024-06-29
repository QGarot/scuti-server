package com.scuti.messages.incoming.handshake;

import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.handshake.UserObjectMessageComposer;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class InfoRetrieveMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        connection.send(new UserObjectMessageComposer(connection.getUser().getDetails()));
    }
}
