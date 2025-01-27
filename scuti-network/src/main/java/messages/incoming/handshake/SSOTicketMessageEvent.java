package messages.incoming.handshake;

import game.IGameController;
import logger.Logger;
import messages.incoming.MessageEvent;
import messages.outgoing.handshake.AuthenticationOKMessageComposer;
import server.connections.IConnection;
import server.streams.IRequest;

public class SSOTicketMessageEvent extends MessageEvent {

    public SSOTicketMessageEvent(IGameController gameController) {
        super(gameController);
    }

    @Override
    public void handle(IConnection connection, IRequest clientMessage) {
        String ticket = clientMessage.popFixedString();
        Logger.logInfo("An user is trying to log with SSO : \"".concat(ticket).concat("\""));

        // the following integer is the user id if the authentication was successfully established, -1 else.
        int authId = this.getGameController().getUserService().authentication(ticket);

        if (authId == -1) {
            // authentication failed
            connection.close();
        } else {
            connection.setUser(this.getGameController().getUserService().getUser(authId));
            connection.send(new AuthenticationOKMessageComposer());
        }
    }
}
