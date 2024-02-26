package com.scuti.server.netty;

import com.scuti.util.logger.Logger;
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

import java.net.InetSocketAddress;

public class NettyServer  {

    final private static int BACK_LOG = 20;
    final private static int BUFFER_SIZE = 2048;

    private DefaultChannelGroup channels;
    private ServerBootstrap bootstrap;
    private String ip;
    private Integer port;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    public NettyServer(String ip, Integer port) {
        this.channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        this.bootstrap = new ServerBootstrap();
        this.ip = ip;
        this.port = port;
    }

    public void createSocket() {
        int threads = Runtime.getRuntime().availableProcessors() * 2;
        this.bossGroup = (Epoll.isAvailable()) ? new EpollEventLoopGroup(threads) : new NioEventLoopGroup(threads);
        this.workerGroup = (Epoll.isAvailable()) ? new EpollEventLoopGroup(threads) : new NioEventLoopGroup(threads);

        this.bootstrap.group(this.bossGroup, this.workerGroup)
                .channel((Epoll.isAvailable()) ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                .childHandler(new NettyChannelInitializer(this))
                .option(ChannelOption.SO_BACKLOG, BACK_LOG)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.SO_RCVBUF, BUFFER_SIZE)
                .childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(BUFFER_SIZE))
                .childOption(ChannelOption.ALLOCATOR, new UnpooledByteBufAllocator(false));
    }

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

    public void dispose() {
        this.workerGroup.shutdownGracefully();
        this.bossGroup.shutdownGracefully();
    }

    public DefaultChannelGroup getChannels() {
        return channels;
    }
}
