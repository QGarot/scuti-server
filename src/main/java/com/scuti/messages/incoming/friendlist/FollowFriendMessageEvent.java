package com.scuti.messages.incoming.friendlist;

import com.scuti.game.rooms.Room;
import com.scuti.game.rooms.RoomManager;
import com.scuti.game.users.User;
import com.scuti.game.users.UserManager;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.rooms.session.RoomForwardMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;

import java.util.Objects;

public class FollowFriendMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        int buddyId = clientMessage.popWiredInt32();
        User buddy = UserManager.getInstance().getUserById(buddyId);
        Room room = RoomManager.getInstance().getRoomsLoaded().get(buddy.getRoomId());

        if (room != null) {
            user.send(new RoomForwardMessageComposer(Objects.equals(room.getDetails().getType(), "public"), room.getDetails().getId()));
        }
    }
}
