package messages.incoming.inventory;

import game.IGameController;
import messages.incoming.MessageEvent;
import messages.outgoing.inventory.CreditBalanceMessageComposer;
import messages.outgoing.notifications.HabboActivityPointNotificationMessageComposer;
import server.connections.IConnection;
import server.streams.IRequest;

public class GetCreditsInfoMessageEvent extends MessageEvent {

    public GetCreditsInfoMessageEvent(IGameController gameController) {
        super(gameController);
    }

    @Override
    public void handle(IConnection connection, IRequest clientMessage) {
        int credits = connection.getUser().getDetails().getCredits();
        int pixels = connection.getUser().getDetails().getPixels();
        int shells = connection.getUser().getDetails().getShells();

        connection.send(new CreditBalanceMessageComposer(credits));
        connection.send(new HabboActivityPointNotificationMessageComposer(0, pixels));
        connection.send(new HabboActivityPointNotificationMessageComposer(4, shells));
    }
}
