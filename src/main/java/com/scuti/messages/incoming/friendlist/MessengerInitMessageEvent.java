package com.scuti.messages.incoming.friendlist;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.friendlist.MessengerInitMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.storage.dao.FriendDao;

public class MessengerInitMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        FriendDao.fillFriendsOf(user);
        user.send(new MessengerInitMessageComposer(user.getMessenger()));
    }
}
