package com.scuti.server.netty.connections;

import com.scuti.game.users.User;
import com.scuti.game.users.UserManager;
import com.scuti.messages.MessageHandler;
import com.scuti.server.netty.NettyPlayerNetwork;
import com.scuti.server.netty.NettyServer;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.util.logger.Logger;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ConnectionHandler extends SimpleChannelInboundHandler<NettyRequest> {

    private NettyServer server;

    public ConnectionHandler(NettyServer server) {
        this.server = server;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Logger.logInfo("Connection from ".concat(ctx.channel().localAddress().toString()));
        User user = new User(new NettyPlayerNetwork(ctx.channel(), ctx.channel().hashCode()));
        UserManager.getInstance().addUser(user);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        this.server.getChannels().remove(ctx.channel());
        //UserManager.getInstance().
        Logger.logInfo("Disconnection from ".concat(ctx.channel().localAddress().toString()));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyRequest message) throws Exception {
        // << MessageHandler.handleRequest(player, message) >>
        Channel channel = ctx.channel();
        User user = UserManager.getInstance().getUserByChannel(channel);
        MessageHandler.getInstance().handle(user, message);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
