package com.scuti.messages.incoming.handshake;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.handshake.AuthenticationOKMessageComposer;
import com.scuti.messages.outgoing.users.MotdNotificationMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.storage.dao.UserDao;
import com.scuti.util.logger.Logger;

public class SSOTicketMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        String ticket = clientMessage.popFixedString();
        Logger.logInfo("An user is trying to log with SSO : \"".concat(ticket).concat("\""));
        if (UserDao.loginSSO(user, ticket)) {
            Logger.logInfo(user.getDetails().getUsername().concat(" is now connected!"));
            user.send(new AuthenticationOKMessageComposer());
            user.send(new MotdNotificationMessageComposer());
        } else {
            user.getNetwork().getChannel().close();
            user.getNetwork().close();
        }
    }
}
