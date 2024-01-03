package com.scuti.messages.incoming.friendlist;

import com.scuti.game.users.User;
import com.scuti.game.users.UserManager;
import com.scuti.game.users.messenger.requests.Request;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.friendlist.NewBuddyRequestMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.storage.dao.FriendDao;

public class RequestBuddyMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        String fromUsername = clientMessage.popFixedString();
        User receiver = UserManager.getInstance().getUserByUsername(fromUsername);

        // TODO: handle disconnected user case

        if (receiver != null) {
            Request request = new Request();
            request.setFromUserId(user.getDetails().getId());
            request.setToUserId(receiver.getDetails().getId());

            FriendDao.insertRequestBuddyAndFillId(request);
            receiver.send(new NewBuddyRequestMessageComposer(request.getId(), user.getDetails().getUsername(), user.getDetails().getFigure()));
        } else {

        }
    }
}
