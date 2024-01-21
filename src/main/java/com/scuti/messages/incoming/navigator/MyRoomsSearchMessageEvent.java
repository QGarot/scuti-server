package com.scuti.messages.incoming.navigator;

import com.scuti.game.rooms.RoomManager;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.navigator.GuestRoomSearchResultMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;

public class MyRoomsSearchMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {

        if (user.getRooms().isEmpty()) {
            RoomManager.getInstance().loadRoomsForUser(user);
        }

        user.send(new GuestRoomSearchResultMessageComposer(user.getRooms()));
    }
}
