package com.scuti.messages.incoming.navigator;

import com.scuti.game.components.navigator.NavigatorComponent;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.navigator.OfficialRoomsMessageComposer;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class GetOfficialRoomsMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        connection.send(new OfficialRoomsMessageComposer(NavigatorComponent.getInstance().getPublicRoomEntries()));
    }
}
