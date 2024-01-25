package com.scuti.messages.incoming.rooms.session;

import com.scuti.game.rooms.Room;
import com.scuti.game.rooms.RoomManager;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.rooms.engine.RoomPropertyMessageComposer;
import com.scuti.messages.outgoing.rooms.session.OpenConnectionMessageComposer;
import com.scuti.messages.outgoing.rooms.session.RoomReadyMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;

public class OpenFlatConnectionMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        int roomId = clientMessage.popWiredInt32();
        Room room = RoomManager.getInstance().getRoomsLoaded().get(roomId);

        user.setRoomId(roomId);

        // TODO: prepare room for user
        user.send(new OpenConnectionMessageComposer(roomId, room.getDetails().getCategory()));

        user.send(new RoomPropertyMessageComposer("landscape", "0.0"));

        user.send(new RoomReadyMessageComposer(roomId, room.getDetails().getModelName()));
    }
}
