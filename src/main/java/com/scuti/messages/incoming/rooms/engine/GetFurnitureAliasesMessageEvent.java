package com.scuti.messages.incoming.rooms.engine;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class GetFurnitureAliasesMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        connection.send(new FurnitureAliasesMessageComposer());
    }
}
