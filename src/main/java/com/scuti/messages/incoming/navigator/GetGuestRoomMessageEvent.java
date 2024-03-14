package com.scuti.messages.incoming.navigator;

import com.scuti.game.rooms.Room;
import com.scuti.game.rooms.RoomManager;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.navigator.GetGuestRoomResultComposer;
import com.scuti.server.netty.streams.NettyRequest;

public class GetGuestRoomMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        int roomId = clientMessage.popWiredInt32();
        boolean loadingState = clientMessage.popWiredBoolean();
        boolean following = clientMessage.popWiredBoolean();

        Room room = RoomManager.getInstance().getRoomById(roomId);

        user.send(new GetGuestRoomResultComposer(loadingState, room, following, false));
    }
}
