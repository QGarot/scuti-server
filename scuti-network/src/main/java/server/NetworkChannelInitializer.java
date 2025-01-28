package server;

import game.IGameController;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import server.handlers.IGameHandler;
import server.codec.Decoder;
import server.codec.Encoder;
import server.handlers.ConnectionHandler;
import server.handlers.MessageHandler;

/**
 * This class creates network handlers. Two main handlers are:
 * - MessageHandler: handles game-related events, also called message events,
 * - ConnectionHandler: manages client-side communication, including incoming packets.
 *  Of course, connection handler depends on message handler because it needs to be able to handle potential game events.
 */
public class NetworkChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final IGameController game;

    public NetworkChannelInitializer(IGameController game) {
        this.game = game;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        IGameHandler messageHandler = new MessageHandler(this.game);
        socketChannel.pipeline().addLast("encoder", new Encoder());
        socketChannel.pipeline().addLast("decoder", new Decoder());
        socketChannel.pipeline().addLast("handler", new ConnectionHandler(messageHandler));
    }
}
