package com.scuti.server.netty.connections;

import com.scuti.server.game.users.User;
import com.scuti.server.netty.NettyPlayerNetwork;
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
        System.out.println("Connection from ".concat(ctx.channel().localAddress().toString()));
        User user = new User(new NettyPlayerNetwork(ctx.channel(), ctx.channel().hashCode()));
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        this.server.getChannels().remove(ctx.channel());
        // Disconnection from...
        System.out.println("Disconnection from ".concat(ctx.channel().localAddress().toString()));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyRequest message) throws Exception {
        // << MessageHandler.handleRequest(player, message) >>
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
