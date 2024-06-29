package com.scuti.messages.incoming.handshake;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.handshake.InitCryptoMessageComposer;
import com.scuti.messages.outgoing.handshake.SessionParamsMessageComposer;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class InitCryptoMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        //user.send(new InitCryptoMessageComposer());
        connection.send(new SessionParamsMessageComposer());
    }
}
