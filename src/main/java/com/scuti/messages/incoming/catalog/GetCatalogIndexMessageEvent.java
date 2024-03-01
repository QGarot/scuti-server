package com.scuti.messages.incoming.catalog;

import com.scuti.game.catalog.CatalogManager;
import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.catalog.CatalogIndexMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;

public class GetCatalogIndexMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        user.send(new CatalogIndexMessageComposer(CatalogManager.getInstance().getCatalogPages()));
    }
}
