package com.scuti.messages.incoming.rooms.engine;

import com.scuti.game.rooms.Room;
import com.scuti.game.rooms.RoomManager;
import com.scuti.game.rooms.RoomModelManager;
import com.scuti.game.rooms.mapping.RoomModel;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.rooms.engine.*;
import com.scuti.server.netty.streams.NettyRequest;

public class GetRoomEntryDataMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        Room room = RoomManager.getInstance().getRoomsLoaded().get(user.getRoomId());
        RoomModel model = RoomModelManager.getInstance().getModels().get(room.getDetails().getModelName());

        System.out.println("name" + model.getName());
        System.out.println(model.getHeightmap());
        // TODO: send
        // - HeightMapMsgComposer
        user.send(new HeightMapMessageComposer(model.getHeightmap()));
        // - FloorHeightmapMsgComposer
        user.send(new FloorHeightMapMessageComposer(model));
        // 3105 (GetUserNotifications, GetRoomAd)
        // - UsersMsgComposer
        user.send(new UsersMessageComposer(user.getDetails()));
        // Check rights (RoomEntryInfoMsgComposer & RoomVisualizationSettingsComposer)
        user.send(new RoomEntryInfoMessageComposer(true, room.getDetails().getId(), true));
        user.send(new RoomVisualizationSettingsComposer(false, 0, 0));
    }
}
