package com.scuti.messages.incoming.catalog;

import com.scuti.game.catalog.CatalogManager;
import com.scuti.game.catalog.CatalogPage;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.catalog.CatalogPageMessageComposer;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class GetCatalogPageMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        int pageId = clientMessage.popWiredInt32();
        CatalogPage page = CatalogManager.getInstance().getCatalogPageById(pageId);
        connection.send(new CatalogPageMessageComposer(page));
    }
}
