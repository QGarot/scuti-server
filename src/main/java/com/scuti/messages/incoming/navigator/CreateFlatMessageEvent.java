package com.scuti.messages.incoming.navigator;

import com.scuti.game.rooms.Room;
import com.scuti.game.rooms.RoomManager;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.server.netty.streams.NettyRequest;

public class CreateFlatMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        String caption = clientMessage.popFixedString();
        String model = clientMessage.popFixedString();

        Room room = new Room();
        room.getDetails().setCaption(caption);
        room.getDetails().setOwnerName(user.getDetails().getUsername());
        room.getDetails().setModelName(model);

        RoomManager.getInstance().getRoomDao().insert(room);

        if (room.getDetails().getId() != 0) {
            RoomManager.getInstance().getRoomsLoaded().add(room);
            user.getRooms().add(room);
        } else {
            // TODO: send error alert to the user
        }
    }
}
