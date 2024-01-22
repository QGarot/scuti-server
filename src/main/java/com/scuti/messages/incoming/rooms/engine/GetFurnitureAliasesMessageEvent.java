package com.scuti.messages.incoming.rooms.engine;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.server.netty.streams.NettyRequest;

public class GetFurnitureAliasesMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        user.send(new FurnitureAliasesMessageComposer());
    }
}
