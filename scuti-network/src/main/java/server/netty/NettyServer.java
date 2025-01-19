package server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import logger.Logger;
import messages.IHandler;
import messages.MessageHandler;
import server.netty.connections.NettyConnectionManager;

import java.net.InetSocketAddress;

public class NettyServer  {

    private final static int BACK_LOG = 20;

    private final static int BUFFER_SIZE = 2048;

    private final DefaultChannelGroup channels;

    private final ServerBootstrap bootstrap;

    private final String ip;

    private final Integer port;

    private int connections;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    private final IHandler messagesHandler;

    private final NettyConnectionManager connectionManager;

    public NettyServer(String ip, Integer port) {
        this.channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        this.bootstrap = new ServerBootstrap();
        this.messagesHandler = new MessageHandler();
        this.connectionManager = new NettyConnectionManager();
        this.ip = ip;
        this.port = port;
        this.connections = 0;
    }

    /**
     * Returns the connections manager
     * @return connections manager
     */
    public NettyConnectionManager getConnectionManager() {
        return this.connectionManager;
    }

    /**
     * Returns the messages handler.
     * @return messages handler instance
     */
    public IHandler getMessagesHandler() {
        return this.messagesHandler;
    }

    /**
     * Initialize event loop groups.
     */
    public void createSocket() {
        int threads = Runtime.getRuntime().availableProcessors() * 2;
        this.bossGroup = (Epoll.isAvailable()) ? new EpollEventLoopGroup(threads) : new NioEventLoopGroup(threads);
        this.workerGroup = (Epoll.isAvailable()) ? new EpollEventLoopGroup(threads) : new NioEventLoopGroup(threads);

        this.bootstrap.group(this.bossGroup, this.workerGroup)
                .channel((Epoll.isAvailable()) ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                .childHandler(new NettyChannelInitializer(this.getConnectionManager(), this.getChannels(), this.getMessagesHandler()))
                .option(ChannelOption.SO_BACKLOG, BACK_LOG)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.SO_RCVBUF, BUFFER_SIZE)
                .childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(BUFFER_SIZE))
                .childOption(ChannelOption.ALLOCATOR, new UnpooledByteBufAllocator(false));
    }

    /**
     * Launches the server.
     */
    public void bind() {
        this.bootstrap.bind(new InetSocketAddress(this.ip, this.port)).addListener(objectFuture -> {
            if (!objectFuture.isSuccess()) {
                Logger.logError("Failed to start server on address: {}:{}");
                System.out.println("Please double check there's no programs using the same port, and you have set the correct IP address to listen on.");
            } else {
                Logger.logInfo("Server is listening on " + this.ip + ":{" + this.port.toString() + "}");
                Logger.logInfo("Ready for connections!");
            }
        });
    }

    /**
     * Shutdown gracefully worker and boss groups.
     */
    public void dispose() {
        this.workerGroup.shutdownGracefully();
        this.bossGroup.shutdownGracefully();
    }

    /**
     * Increment the number of connected clients.
     */
    public void incrementConnections() {
        this.connections = this.connections + 1;
    }

    /**
     * Decrement the number of connected clients.
     */
    public void decrementConnections() {
        this.connections = this.connections - 1;
    }

    /**
     * Returns the number of connected clients.
     * @return number of clients
     */
    public int getConnections() {
        return this.connections;
    }

    /**
     * Returns the channel group.
     * @return default channel group
     */
    public DefaultChannelGroup getChannels() {
        return channels;
    }
}
