package com.scuti.messages.incoming.rooms.session;

import com.scuti.game.rooms.Room;
import com.scuti.game.rooms.RoomManager;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.rooms.engine.RoomPropertyMessageComposer;
import com.scuti.messages.outgoing.rooms.session.OpenConnectionMessageComposer;
import com.scuti.messages.outgoing.rooms.session.RoomReadyMessageComposer;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class OpenFlatConnectionMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        int roomId = clientMessage.popWiredInt32();

        // User has to leave the current room
        int currentRoomId = connection.getUser().getRoomId();
        if (currentRoomId != 0) {
            Room currentRoom = RoomManager.getInstance().getRoomById(currentRoomId);
            if (currentRoom != null) {
                currentRoom.getEntityManager().disposeRoomUser(connection.getUser().getDetails().getId());
                currentRoom.getDetails().setUsersNow(currentRoom.getDetails().getUsersNow() - 1);
                System.out.println(connection.getUser().getDetails().getUsername() + " just leaves " + currentRoom.getDetails().getCaption());
                RoomManager.getInstance().getRoomDao().save(currentRoom);
            }
        }

        // Prepare room for user (TODO: link it do database...)
        Room room = RoomManager.getInstance().getRoomById(roomId);
        if (room != null) {
            connection.getUser().setRoomId(roomId);
            room.getDetails().setUsersNow(room.getDetails().getUsersNow() + 1);
            connection.send(new OpenConnectionMessageComposer(roomId, room.getDetails().getCategory()));
            connection.send(new RoomPropertyMessageComposer("wallpaper", "901"));
            connection.send(new RoomPropertyMessageComposer("floor", "401"));
            connection.send(new RoomPropertyMessageComposer("landscape", "5.3"));
            connection.send(new RoomReadyMessageComposer(roomId, room.getDetails().getModelName()));
            RoomManager.getInstance().getRoomDao().save(room);
        }
    }
}
