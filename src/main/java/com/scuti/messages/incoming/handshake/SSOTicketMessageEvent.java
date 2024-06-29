package com.scuti.messages.incoming.handshake;

import com.scuti.game.users.User;
import com.scuti.game.users.UserManager;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.util.logger.Logger;

public class SSOTicketMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        String ticket = clientMessage.popFixedString();
        Logger.logInfo("An user is trying to log with SSO : \"".concat(ticket).concat("\""));
        UserManager.getInstance().loginSSO(connection, ticket);
    }
}
