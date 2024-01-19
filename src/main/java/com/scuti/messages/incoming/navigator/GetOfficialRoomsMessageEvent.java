package com.scuti.messages.incoming.navigator;

import com.scuti.game.navigator.PublicRoomEntry;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.navigator.OfficialRoomsMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.storage.dao.NavigatorDao;

import java.util.List;

public class GetOfficialRoomsMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        List<PublicRoomEntry> publicRooms = NavigatorDao.getPublicRooms();
        user.send(new OfficialRoomsMessageComposer(publicRooms));
    }
}
