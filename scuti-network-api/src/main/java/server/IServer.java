package server;

import game.IGameController;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import messages.IGameHandler;
import server.streams.IRequest;

public interface IServer {

    /**
     * Runs the server.
     */
    void run();

    /**
     * Returns the game controller, known by the server module.
     * @return game controller
     */
    IGameController getGame();

    /**
     * Returns the connection handler, handling any received data from the client side.
     * @return connection handler
     */
    SimpleChannelInboundHandler<IRequest> getConnectionHandler();

    /**
     * Returns the message handler, handling game events (e.g. SSOTicketMessageEvent).
     * @return message handler
     */
    IGameHandler getMessageHandler();

    /**
     * Returns the encoder.
     * @return encoder
     */
    MessageToMessageEncoder<byte[]> getEncoder();

    /**
     * Returns the decoder.
     * @return decoder
     */
    ByteToMessageDecoder getDecoder();
}
