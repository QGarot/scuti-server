package com.scuti.server.netty.connections;

import com.scuti.server.netty.NettyServer;
import com.scuti.server.netty.streams.NettyRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ConnectionHandler extends SimpleChannelInboundHandler<NettyRequest> {

    private NettyServer server;

    public ConnectionHandler(NettyServer server) {
        this.server = server;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("New client connected!");
        System.out.println(ctx.channel().remoteAddress().toString());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyRequest message) throws Exception {

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
