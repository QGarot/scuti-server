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

        // User has to leave the current room
        int currentRoomId = user.getRoomId();
        if (currentRoomId != 0) {
            Room currentRoom = RoomManager.getInstance().getRoomsLoaded().get(currentRoomId);
            if (currentRoom != null) {
                currentRoom.getEntityManager().disposeRoomUser(user.getDetails().getId());
                currentRoom.getDetails().setUsersNow(currentRoom.getDetails().getUsersNow() - 1);
                System.out.println(user.getDetails().getUsername() + " just leaves " + currentRoom.getDetails().getCaption());
                RoomManager.getInstance().getRoomDao().saveRoomDetails(currentRoom.getDetails());
            }
        }

        // Prepare room for user (TODO: link it do database...)
        Room room = RoomManager.getInstance().getRoomsLoaded().get(roomId);
        if (room != null) {
            user.setRoomId(roomId);
            room.getDetails().setUsersNow(room.getDetails().getUsersNow() + 1);
            user.send(new OpenConnectionMessageComposer(roomId, room.getDetails().getCategory()));
            user.send(new RoomPropertyMessageComposer("wallpaper", "901"));
            user.send(new RoomPropertyMessageComposer("floor", "401"));
            user.send(new RoomPropertyMessageComposer("landscape", "5.3"));
            user.send(new RoomReadyMessageComposer(roomId, room.getDetails().getModelName()));
            RoomManager.getInstance().getRoomDao().saveRoomDetails(room.getDetails());
        }
    }
}
