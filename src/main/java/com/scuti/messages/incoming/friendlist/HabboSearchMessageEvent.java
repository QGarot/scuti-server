package com.scuti.messages.incoming.friendlist;

import com.scuti.game.users.UserManager;
import com.scuti.game.users.components.friendship.users.UserSearched;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.friendlist.HabboSearchMessageComposer;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

import java.util.ArrayList;
import java.util.List;

public class HabboSearchMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        String username = clientMessage.popFixedString();
        List<UserSearched> users = UserManager.getInstance().getUserFriendshipsDao().getUsersByUsername(username);

        List<UserSearched> friends = new ArrayList<>();
        List<UserSearched> others = new ArrayList<>();

        for (UserSearched userSearched: users) {
            if (connection.getUser().getFriendship().isBuddy(userSearched.getId())) {
                friends.add(userSearched);
            } else {
                others.add(userSearched);
            }
        }

        connection.send(new HabboSearchMessageComposer(friends, others));
    }
}
