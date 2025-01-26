package messages.incoming;

import game.IGameController;
import server.connections.IConnection;
import server.streams.IRequest;

/**
 * A client event abstraction representing an incoming message to treat.
 */
public abstract class MessageEvent {

    private IGameController gameController;

    /**
     * Returns the game manager instance.
     * @return game
     */
    public IGameController getGameController() {
        return this.gameController;
    }

    /**
     * Treats the message event.
     * @param connection: the client who triggers the event
     * @param clientMessage: the incoming message ready to be read
     */
    public abstract void handle(IConnection connection, IRequest clientMessage);
}
