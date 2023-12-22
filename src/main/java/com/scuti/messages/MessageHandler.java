package com.scuti.messages;

import com.scuti.messages.incoming.MessageEvent;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.game.users.User;
import com.scuti.util.logger.Logger;

import java.util.HashMap;

public class MessageHandler {
    private HashMap<int, MessageEvent> packets;
    private static MessageHandler instance;

    private MessageHandler() {
        this.packets = new HashMap<int, MessageEvent>();
    }

    public void handle(User user, NettyRequest clientMessage) {
        int header = clientMessage.getHeader();
        if (this.packets.containsKey(header)) {
            this.packets.get(header).handle(user, clientMessage);
            Logger.logIncoming(header);
        }
    }

    public static MessageHandler getInstance() {
        if (instance == null) {
            instance = new MessageHandler();
        }

        return instance;
    }
}
