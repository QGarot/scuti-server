package com.scuti.messages.incoming.friendlist;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.friendlist.MessengerInitMessageComposer;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class MessengerInitMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        //FriendDao.fillFriendsOf(user);
        connection.send(new MessengerInitMessageComposer(connection.getUser().getFriendship()));
    }
}
