package com.scuti.messages.incoming.register;

import com.scuti.game.users.User;
import com.scuti.game.users.UserManager;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.server.netty.streams.NettyRequest;

public class UpdateFigureDataMessageEvent extends MessageEvent {

    @Override
    public void handle(User user, NettyRequest clientMessage) {
        // TODO: check get string & update avatar status in the room
        String gender = clientMessage.popFixedString().toUpperCase();
        String figure = clientMessage.popFixedString();

        System.out.println("gender: " + gender);
        System.out.println("look: " + figure);

        user.getDetails().setFigure(figure);
        user.getDetails().setSex(gender);
        UserManager.getInstance().getUserDao().saveDetails(user);
    }
}
