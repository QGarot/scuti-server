package com.scuti.messages.incoming.friendlist;

import com.scuti.game.users.User;
import com.scuti.game.users.friends.Buddy;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.friendlist.HabboSearchMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.storage.dao.FriendDao;

import java.util.ArrayList;
import java.util.List;

public class HabboSearchMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        String username = clientMessage.popFixedString();
        List<Buddy> buddies = FriendDao.getBuddiesByUsername(username);

        List<Buddy> friends = new ArrayList<>();
        List<Buddy> others = new ArrayList<>();

        for (Buddy buddy: buddies) {
            if (user.getMessenger().isBuddy(buddy.getId())) {
                friends.add(buddy);
            } else {
                others.add(buddy);
            }
        }

        user.send(new HabboSearchMessageComposer(friends, others));
    }
}
