package com.scuti.messages.incoming;

import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.game.users.User;

public abstract class MessageEvent {
    public abstract void handle(User user, NettyRequest clientMessage);
}
