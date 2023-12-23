package com.scuti.messages.incoming.handshake;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.util.logger.Logger;

public class InitCryptoMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        Logger.debug("cc");
    }
}
