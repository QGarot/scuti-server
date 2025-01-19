package messages.incoming;

import server.connections.IConnection;
import server.streams.IRequest;

/**
 * A client event abstraction representing an incoming message to treat.
 */
public abstract class MessageEvent {

    private Object gameManager;

    /**
     * Returns the game manager instance.
     * @return game
     */
    public Object getGameManager() {
        return this.gameManager;
    }

    /**
     * Treats the message event.
     * @param connection: the client who triggers the event
     * @param clientMessage: the incoming message ready to be read
     */
    public abstract void handle(IConnection connection, IRequest clientMessage);
}
