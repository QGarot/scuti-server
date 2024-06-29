package com.scuti.messages.incoming.rooms.engine;

import com.scuti.game.rooms.Room;
import com.scuti.game.rooms.RoomManager;
import com.scuti.game.rooms.RoomModelManager;
import com.scuti.game.rooms.entities.RoomUser;
import com.scuti.game.rooms.mapping.RoomModel;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.rooms.engine.*;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

import java.util.Objects;

public class GetRoomEntryDataMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        Room room = RoomManager.getInstance().getRoomById(connection.getUser().getRoomId());
        RoomModel model = RoomModelManager.getInstance().getModelByName(room.getDetails().getModelName());

        int x = model.getDoorX();
        int y = model.getDoorY();
        int z = model.getDoorZ();
        int rotation = model.getDoorRotation();
        RoomUser roomUser = new RoomUser(connection.getUser().getDetails().getId(), x, y, z, rotation);
        room.getEntityManager().addRoomUser(roomUser);

        System.out.println(connection.getUser().getDetails().getUsername() + " goes into " + room.getDetails().getCaption());
        System.out.println("- Entities : " + room.getEntityManager().getRoomUsers());
        System.out.println(" - virtualid : " + roomUser.getVirtualId());

        // - HeightMapMsgComposer
        connection.send(new HeightMapMessageComposer(model.getHeightmap()));
        // - FloorHeightmapMsgComposer
        connection.send(new FloorHeightMapMessageComposer(model));
        // 3105, 126 (GetUserNotifications, GetRoomAd)
        // - UsersMsgComposer
        connection.send(new UsersMessageComposer(connection.getUser().getDetails(), roomUser));
        // Check rights (RoomEntryInfoMsgComposer & RoomVisualizationSettingsComposer)
        connection.send(new RoomEntryInfoMessageComposer(Objects.equals(room.getDetails().getType(), "private"), room.getDetails().getId(), true));
        connection.send(new RoomVisualizationSettingsComposer(false, 0, 0));

        connection.send(new UserUpdateMessageComposer(room.getEntityManager().getRoomUsers()));
    }
}
