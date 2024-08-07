package com.scuti.messages.incoming.navigator;

import com.scuti.game.rooms.Room;
import com.scuti.game.rooms.RoomManager;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class CreateFlatMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        String caption = clientMessage.popFixedString();
        String model = clientMessage.popFixedString();

        Room room = new Room();
        room.getDetails().setCaption(caption);
        room.getDetails().setOwnerName(connection.getUser().getDetails().getUsername());
        room.getDetails().setModelName(model);

        RoomManager.getInstance().getRoomDao().insert(room);

        if (room.getDetails().getId() != 0) {
            RoomManager.getInstance().getRoomsLoaded().add(room);
            connection.getUser().getRooms().add(room);
        } else {
            // TODO: send error alert to the user
        }
    }
}
