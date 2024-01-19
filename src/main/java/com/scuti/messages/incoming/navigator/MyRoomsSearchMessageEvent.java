package com.scuti.messages.incoming.navigator;

import com.scuti.game.navigator.RoomNavigator;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.navigator.GuestRoomSearchResultMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.storage.dao.NavigatorDao;

import java.util.List;

public class MyRoomsSearchMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        List<RoomNavigator> guestRooms = NavigatorDao.getRoomsByOwnerUsername(user.getDetails().getUsername());
        user.send(new GuestRoomSearchResultMessageComposer(guestRooms));
    }
}
