package messages.incoming.handshake;

import game.IGameController;
import messages.incoming.MessageEvent;
import messages.outgoing.handshake.UserObjectMessageComposer;
import server.connections.IConnection;
import server.streams.IRequest;

public class InfoRetrieveMessageEvent extends MessageEvent {

    public InfoRetrieveMessageEvent(IGameController gameController) {
        super(gameController);
    }

    @Override
    public void handle(IConnection connection, IRequest clientMessage) {
        connection.send(new UserObjectMessageComposer(connection.getUser().getDetails()));
    }
}
