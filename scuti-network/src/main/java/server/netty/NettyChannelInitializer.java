package server.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import messages.IHandler;
import server.netty.codec.NetworkDecoder;
import server.netty.codec.NetworkEncoder;
import server.netty.connections.ConnectionHandler;
import server.netty.connections.NettyConnectionManager;

public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final NettyConnectionManager connectionManager;

    private final DefaultChannelGroup channels;

    private final IHandler messageHandler;

    public NettyChannelInitializer(NettyConnectionManager connectionManager, DefaultChannelGroup channels, IHandler messageHandler) {
        this.connectionManager = connectionManager;
        this.channels = channels;
        this.messageHandler = messageHandler;
    }

    /**
     * Returns the message handler.
     * @return message handler
     */
    private IHandler getMessageHandler() {
        return this.messageHandler;
    }

    /**
     * Returns the channel group.
     * @return default channel group
     */
    private DefaultChannelGroup getChannels() {
        return this.channels;
    }

    /**
     * Returns the connections manager.
     * @return connection manager instance
     */
    private NettyConnectionManager getConnectionManager() {
        return this.connectionManager;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("gameEncoder", new NetworkEncoder());
        pipeline.addLast("gameDecoder", new NetworkDecoder());
        pipeline.addLast("handler", new ConnectionHandler(this.getConnectionManager(), this.getChannels(), this.getMessageHandler()));
    }
}
