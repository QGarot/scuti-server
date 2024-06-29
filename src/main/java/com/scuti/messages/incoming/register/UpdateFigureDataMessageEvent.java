package com.scuti.messages.incoming.register;

import com.scuti.game.users.User;
import com.scuti.game.users.UserManager;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class UpdateFigureDataMessageEvent extends MessageEvent {

    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        // TODO: check get string & update avatar status in the room
        String gender = clientMessage.popFixedString().toUpperCase();
        String figure = clientMessage.popFixedString();

        System.out.println("gender: " + gender);
        System.out.println("look: " + figure);

        connection.getUser().getDetails().setFigure(figure);
        connection.getUser().getDetails().setSex(gender);
        UserManager.getInstance().getUserDetailsDao().save(connection.getUser().getDetails());
    }
}
