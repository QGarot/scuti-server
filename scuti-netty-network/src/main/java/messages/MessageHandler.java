package messages;

import game.IGameController;
import server.connections.IConnection;
import server.streams.IRequest;

public class MessageHandler implements IGameHandler {

    private final IGameController game; // message handler needs to know game logic to manages incoming events

    public MessageHandler(IGameController game) {
        this.game = game;
    }

    /**
     * Returns game controller
     * @return game controller
     */
    private IGameController getGame() {
        return this.game;
    }

    @Override
    public void handle(IConnection connection, IRequest clientMessage) {

    }
}
