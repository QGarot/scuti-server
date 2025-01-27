package server;

import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import game.IGameController;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import logger.Logger;

import java.net.InetSocketAddress;

public class ScutiServer implements IServer {

    private final String HOST;
    private final int PORT;
    private final IGameController game; // the server needs to know the game logic to treats incoming data
    private final ChannelInitializer<SocketChannel> channelInitializer;

    public ScutiServer(String host, int port, IGameController gameController) {
        this.HOST = host;
        this.PORT = port;
        this.game = gameController;
        this.channelInitializer = new NetworkChannelInitializer(this.game);
    }

    @Override
    public void run() {
        int threads = Runtime.getRuntime().availableProcessors() * 2;
        EventLoopGroup bossGroup = (Epoll.isAvailable()) ? new EpollEventLoopGroup(threads) : new NioEventLoopGroup(threads);
        EventLoopGroup workerGroup = (Epoll.isAvailable()) ? new EpollEventLoopGroup(threads) : new NioEventLoopGroup(threads);

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                .channel((Epoll.isAvailable()) ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                .childHandler(this.channelInitializer)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.SO_RCVBUF, 2048)
                .childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(2048))
                .childOption(ChannelOption.ALLOCATOR, new UnpooledByteBufAllocator(false));

            // Bind and start to accept incoming connections.
            b.bind(new InetSocketAddress(this.HOST, this.PORT)).addListener(objectFuture -> {
                if (objectFuture.isSuccess()) {
                    Logger.logInfo("Scuti server is listening on " + this.HOST + ":" + this.PORT);
                } else {
                    Logger.logError("Scuti server failed to start!");
                }
            });
    }
}
