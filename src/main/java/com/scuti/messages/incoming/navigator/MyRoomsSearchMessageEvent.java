package com.scuti.messages.incoming.navigator;

import com.scuti.game.rooms.RoomManager;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.navigator.GuestRoomSearchResultMessageComposer;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class MyRoomsSearchMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {

        if (connection.getUser().getRooms().isEmpty()) {
            RoomManager.getInstance().loadRoomsForUser(connection.getUser());
        }

        connection.send(new GuestRoomSearchResultMessageComposer(connection.getUser().getRooms()));
    }
}
