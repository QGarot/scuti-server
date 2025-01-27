package messages.incoming;

import game.IGameController;
import server.connections.IConnection;
import server.streams.IRequest;

/**
 * A client event abstraction representing an incoming message to treat.
 */
public abstract class MessageEvent {

    private final IGameController gameController;

    public MessageEvent(IGameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Returns the game controller instance.
     * @return game
     */
    protected IGameController getGameController() {
        return this.gameController;
    }

    /**
     * Treats the message event.
     * @param connection: the client who triggers the event
     * @param clientMessage: the incoming message ready to be read
     */
    public abstract void handle(IConnection connection, IRequest clientMessage);
}
