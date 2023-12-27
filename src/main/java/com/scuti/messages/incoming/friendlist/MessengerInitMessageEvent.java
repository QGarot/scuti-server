package com.scuti.messages.incoming.friendlist;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.friendlist.MessengerInitMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;

public class MessengerInitMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        user.send(new MessengerInitMessageComposer());
    }
}
