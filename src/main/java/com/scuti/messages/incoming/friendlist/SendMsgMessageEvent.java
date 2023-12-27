package com.scuti.messages.incoming.friendlist;

import com.scuti.game.users.User;
import com.scuti.game.users.UserManager;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.friendlist.NewConsoleMsgMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;

public class SendMsgMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        int toUserId = clientMessage.popWiredInt32();
        String message = clientMessage.popFixedString();

        User toUser = UserManager.getInstance().getUserById(toUserId);
        if (toUser != null) {
            toUser.send(new NewConsoleMsgMessageComposer(user.getDetails().getId(), message));
        }
    }
}
