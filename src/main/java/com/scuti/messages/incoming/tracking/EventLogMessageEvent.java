package com.scuti.messages.incoming.tracking;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.server.netty.streams.NettyRequest;

public class EventLogMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        String category = clientMessage.popFixedString(); // login
        String type = clientMessage.popFixedString(); // socket
        String action = clientMessage.popFixedString(); // client.auth_ok
    }
}
