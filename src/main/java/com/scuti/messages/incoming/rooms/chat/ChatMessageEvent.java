package com.scuti.messages.incoming.rooms.chat;

import com.scuti.game.commands.CommandManager;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class ChatMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        String message = clientMessage.popFixedString();

        // for some tests
        String[] splitMessage = message.split(" ");
        if (CommandManager.getInstance().getCommands().containsKey(splitMessage[0])) {
            CommandManager.getInstance().getCommands().get(splitMessage[0]).handle(connection.getUser(), null);
        }

        if (message.equals("kick")) {
            System.out.println("auto kick");
            connection.disconnect();
        }
    }
}
