package com.scuti.messages.incoming.navigator;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.server.netty.streams.NettyRequest;

public class PopularRoomsSearchMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        String mode = clientMessage.popFixedString();
    }
}
