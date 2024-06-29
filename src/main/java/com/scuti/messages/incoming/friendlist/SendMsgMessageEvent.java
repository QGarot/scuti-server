package com.scuti.messages.incoming.friendlist;

import com.scuti.game.users.User;
import com.scuti.game.users.UserManager;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.friendlist.NewConsoleMsgMessageComposer;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.util.logger.Logger;

public class SendMsgMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        int toUserId = clientMessage.popWiredInt32();
        String message = clientMessage.popFixedString();

        User toUser = UserManager.getInstance().getUserById(toUserId);
        if (toUser != null) {
            //toUser.send(new NewConsoleMsgMessageComposer(connection.getUser().getDetails().getId(), message));
        } else {
            // TODO
            Logger.logWarning("Cannot send console message to this user!");
        }
    }
}
