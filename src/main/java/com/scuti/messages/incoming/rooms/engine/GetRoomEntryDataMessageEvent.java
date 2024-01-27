package com.scuti.messages.incoming.rooms.engine;

import com.scuti.game.rooms.Room;
import com.scuti.game.rooms.RoomManager;
import com.scuti.game.rooms.RoomModelManager;
import com.scuti.game.rooms.entities.RoomUser;
import com.scuti.game.rooms.mapping.RoomModel;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.rooms.engine.*;
import com.scuti.server.netty.streams.NettyRequest;

import java.util.Objects;

public class GetRoomEntryDataMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        Room room = RoomManager.getInstance().getRoomsLoaded().get(user.getRoomId());
        RoomModel model = RoomModelManager.getInstance().getModels().get(room.getDetails().getModelName());

        int x = model.getDoorX();
        int y = model.getDoorY();
        int z = model.getDoorZ();
        int rotation = model.getDoorRotation();
        RoomUser roomUser = new RoomUser(user.getDetails().getId(), x, y, z, rotation);
        room.getEntityManager().addRoomUser(roomUser);

        // - HeightMapMsgComposer
        user.send(new HeightMapMessageComposer(model.getHeightmap()));
        // - FloorHeightmapMsgComposer
        user.send(new FloorHeightMapMessageComposer(model));
        // 3105, 126 (GetUserNotifications, GetRoomAd)
        // - UsersMsgComposer
        user.send(new UsersMessageComposer(user.getDetails(), roomUser));
        // Check rights (RoomEntryInfoMsgComposer & RoomVisualizationSettingsComposer)
        user.send(new RoomEntryInfoMessageComposer(Objects.equals(room.getDetails().getType(), "private"), room.getDetails().getId(), true));
        user.send(new RoomVisualizationSettingsComposer(false, 0, 0));

        user.send(new UserUpdateMessageComposer(room.getEntityManager().getRoomUsers()));
    }
}
