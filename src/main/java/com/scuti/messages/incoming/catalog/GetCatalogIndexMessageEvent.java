package com.scuti.messages.incoming.catalog;

import com.scuti.game.catalog.CatalogManager;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.catalog.CatalogIndexMessageComposer;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class GetCatalogIndexMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        // just for some tests
        User user = connection.getUser();
        int pageIdsMinRank = 1;
        connection.send(new CatalogIndexMessageComposer(CatalogManager.getInstance().getCatalogPages(), user.getDetails().getRank() >= pageIdsMinRank));
    }
}
