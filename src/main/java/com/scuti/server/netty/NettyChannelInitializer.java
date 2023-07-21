package com.scuti.server.netty;

import com.scuti.server.netty.codec.NetworkDecoder;
import com.scuti.server.netty.codec.NetworkEncoder;
import com.scuti.server.netty.connections.ConnectionHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final NettyServer nettyServer;

    public NettyChannelInitializer(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("gameEncoder", new NetworkEncoder());
        pipeline.addLast("gameDecoder", new NetworkDecoder());
        pipeline.addLast("handler", new ConnectionHandler(this.nettyServer));
    }
}
