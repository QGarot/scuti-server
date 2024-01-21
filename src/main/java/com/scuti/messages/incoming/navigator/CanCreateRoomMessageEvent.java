package com.scuti.messages.incoming.navigator;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.navigator.CanCreateRoomMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;

public class CanCreateRoomMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        user.send(new CanCreateRoomMessageComposer(0, 500));
    }
}
