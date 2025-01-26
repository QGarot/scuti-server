package messages;

import server.connections.IConnection;
import server.streams.IRequest;

/**
 * A handler for messages coming from the Habbo R63A client
 */
public interface IGameHandler {
    /**
     * Handles incoming packet.
     * @param connection: the client who sends the message
     * @param clientMessage: the incoming message ready to be read
     */
    void handle(IConnection connection, IRequest clientMessage);
}
