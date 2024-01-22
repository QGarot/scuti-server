package com.scuti.messages.incoming.rooms.engine;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.server.netty.streams.NettyRequest;

public class GetRoomEntryDataMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        // TODO: send
        // - HeightMapMsgComposer
        // - FloorHeightmapMsgComposer
        // - UsersMsgComposer
        // Check rights (RoomEntryInfoMsgComposer & RoomVisualizationSettingsComposer)
    }
}
