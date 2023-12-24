package com.scuti.messages;

import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.incoming.handshake.InitCryptoMessageEvent;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.game.users.User;
import com.scuti.util.logger.Logger;

import java.util.HashMap;

public class MessageHandler {
    private HashMap<Integer, MessageEvent> packets;
    private static MessageHandler instance;

    private MessageHandler() {
        this.packets = new HashMap<Integer, MessageEvent>();

        // Handshake
        this.registerHandshake();
    }

    private void registerHandshake() {
        this.packets.put(206, new InitCryptoMessageEvent());
    }

    public void handle(User user, NettyRequest clientMessage) {
        int header = clientMessage.getHeader();
        if (this.packets.containsKey(header)) {
            Logger.logIncoming(header);
            this.packets.get(header).handle(user, clientMessage);
        } else {
            Logger.logWarning("This packet cannot be handled!");
        }
    }

    public static MessageHandler getInstance() {
        if (instance == null) {
            instance = new MessageHandler();
        }

        return instance;
    }
}
