package com.scuti.messages.incoming.inventory.purse;

import com.scuti.game.users.User;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.inventory.purse.CreditBalanceMessageComposer;
import com.scuti.messages.outgoing.notifications.HabboActivityPointNotificationMessageComposer;
import com.scuti.server.netty.streams.NettyRequest;

public class GetCreditsInfoMessageEvent extends MessageEvent {
    @Override
    public void handle(User user, NettyRequest clientMessage) {
        int credits = user.getDetails().getCredits();
        int pixels = user.getDetails().getPixels();
        int shells = user.getDetails().getShells();

        user.send(new CreditBalanceMessageComposer(credits));
        user.send(new HabboActivityPointNotificationMessageComposer(0, pixels));
        user.send(new HabboActivityPointNotificationMessageComposer(4, shells));
    }
}
