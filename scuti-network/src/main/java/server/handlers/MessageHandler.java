package server.handlers;

import game.IGameController;
import logger.Logger;
import messages.incoming.IncomingHeaders;
import messages.incoming.MessageEvent;
import messages.incoming.handshake.InfoRetrieveMessageEvent;
import messages.incoming.handshake.InitCryptoMessageEvent;
import messages.incoming.handshake.SSOTicketMessageEvent;
import server.connections.IConnection;
import server.streams.IRequest;

import java.util.HashMap;

public class MessageHandler implements IGameHandler {

    private final IGameController game; // message handler needs to know game logic to manages incoming events
    private final HashMap<Integer, MessageEvent> events; // all game events

    public MessageHandler(IGameController game) {
        this.game = game;
        this.events = new HashMap<>();
        this.loadGameEvents();
    }

    /**
     * Returns all registered game events.
     * @return game events ( =: message event)
     */
    private HashMap<Integer, MessageEvent> getEvents() {
        return this.events;
    }

    /**
     * Returns game controller.
     * @return game controller
     */
    private IGameController getGame() {
        return this.game;
    }

    /**
     * Loads all game events.
     */
    private void loadGameEvents() {
        // Handshake
        this.getEvents().put(IncomingHeaders.SSO_TICKET, new SSOTicketMessageEvent(this.getGame()));
        this.getEvents().put(IncomingHeaders.INIT_CRYPTO, new InitCryptoMessageEvent(this.getGame()));
        this.getEvents().put(IncomingHeaders.INFO_RETRIEVE, new InfoRetrieveMessageEvent(this.getGame()));

        // Catalog

        // Register

        // Room

        // Navigator

        // Inventory

        // Tracking

        // Friend list
    }

    @Override
    public void handle(IConnection connection, IRequest clientMessage) {
        int header = clientMessage.getHeader();
        if (this.getEvents().containsKey(header)) {
            Logger.logIncoming(header);
            this.getEvents().get(header).handle(connection, clientMessage);
        } else {
            Logger.logWarning("No event is attached to the header ".concat(String.valueOf(header)));
        }
    }
}
