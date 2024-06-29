package com.scuti.messages.incoming.inventory.purse;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.inventory.purse.CreditBalanceMessageComposer;
import com.scuti.messages.outgoing.notifications.HabboActivityPointNotificationMessageComposer;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class GetCreditsInfoMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        int credits = connection.getUser().getDetails().getCredits();
        int pixels = connection.getUser().getDetails().getPixels();
        int shells = connection.getUser().getDetails().getShells();

        connection.send(new CreditBalanceMessageComposer(credits));
        connection.send(new HabboActivityPointNotificationMessageComposer(0, pixels));
        connection.send(new HabboActivityPointNotificationMessageComposer(4, shells));
    }
}
