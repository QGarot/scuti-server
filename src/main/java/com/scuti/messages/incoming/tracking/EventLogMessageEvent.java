package com.scuti.messages.incoming.tracking;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class EventLogMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        String category = clientMessage.popFixedString(); // login
        String type = clientMessage.popFixedString(); // socket
        String action = clientMessage.popFixedString(); // client.auth_ok

        //System.out.println("Category: " + category);
        //System.out.println("Type: " + type);
        //System.out.println("Action: " + action);
    }
}
