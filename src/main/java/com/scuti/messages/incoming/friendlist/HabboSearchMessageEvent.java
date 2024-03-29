package com.scuti.messages.incoming.friendlist;

import com.scuti.game.users.User;
import com.scuti.game.users.UserManager;
import com.scuti.game.users.components.friendship.users.UserSearched;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.friendlist.HabboSearchMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;

import java.util.ArrayList;
import java.util.List;

public class HabboSearchMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        String username = clientMessage.popFixedString();
        List<UserSearched> users = UserManager.getInstance().getUserFriendshipsDao().getUsersByUsername(username);

        List<UserSearched> friends = new ArrayList<>();
        List<UserSearched> others = new ArrayList<>();

        for (UserSearched userSearched: users) {
            if (user.getFriendship().isBuddy(userSearched.getId())) {
                friends.add(userSearched);
            } else {
                others.add(userSearched);
            }
        }

        user.send(new HabboSearchMessageComposer(friends, others));
    }
}
