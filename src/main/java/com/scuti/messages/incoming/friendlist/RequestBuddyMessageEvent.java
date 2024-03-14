package com.scuti.messages.incoming.friendlist;

import com.scuti.game.users.User;
import com.scuti.game.users.UserManager;
import com.scuti.game.users.components.friendship.requests.Request;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.friendlist.NewBuddyRequestMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;

public class RequestBuddyMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        String receiverUsername = clientMessage.popFixedString();
        User addressee = UserManager.getInstance().getUserByUsername(receiverUsername);

        // TODO: handle disconnected user case
        if (addressee != null) {
            // Create and add a new friend request for the addressee
            Request request = new Request(user.getDetails().getId());
            addressee.getFriendship().addRequest(request);
            UserManager.getInstance().getUserFriendshipsDao().insertFriendRequest(addressee.getId(), request);

            // send notification
            addressee.send(new NewBuddyRequestMessageComposer(request.getId(), user.getDetails().getUsername(), user.getDetails().getFigure()));
        } else {

        }
    }
}
