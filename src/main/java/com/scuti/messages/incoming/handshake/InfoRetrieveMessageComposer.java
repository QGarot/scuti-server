package com.scuti.messages.incoming.handshake;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.handshake.UserObjectMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;

public class InfoRetrieveMessageComposer extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        user.send(new UserObjectMessageComposer(user.getDetails()));
    }
}
