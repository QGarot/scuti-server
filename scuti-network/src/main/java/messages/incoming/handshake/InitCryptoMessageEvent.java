package messages.incoming.handshake;

import game.IGameController;
import messages.incoming.MessageEvent;
import messages.outgoing.handshake.SessionParamsMessageComposer;
import server.connections.IConnection;
import server.streams.IRequest;

public class InitCryptoMessageEvent extends MessageEvent {

    public InitCryptoMessageEvent(IGameController gameController) {
        super(gameController);
    }

    @Override
    public void handle(IConnection connection, IRequest clientMessage) {
        // TODO: send InitCryptoMsgComposer
        connection.send(new SessionParamsMessageComposer());
    }
}
