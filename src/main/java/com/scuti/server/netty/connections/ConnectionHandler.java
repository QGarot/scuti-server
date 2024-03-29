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

import java.util.Objects;

public class ConnectionHandler extends SimpleChannelInboundHandler<NettyRequest> {

    private final NettyServer server;

    public ConnectionHandler(NettyServer server) {
        this.server = server;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        NettyPlayerNetwork network = new NettyPlayerNetwork(ctx.channel(), ctx.channel().hashCode());

        int maxConnectionPerIp = 2;
        int count = 0;
        for (User user: UserManager.getInstance().getUsers()) {
            if (Objects.equals(user.getNetwork().getIpAddress(), network.getIpAddress())) {
                count = count + 1;
            }
        }

        User user = new User(network);
        UserManager.getInstance().getUsers().add(user);
        if (count >= maxConnectionPerIp) {
            user.disconnect(1);
            Logger.logInfo("Connection failed from ".concat(user.getNetwork().getIpAddress()).concat(". Max connection per ip is ".concat(String.valueOf(maxConnectionPerIp))));
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        this.server.getChannels().remove(ctx.channel());
        User user = UserManager.getInstance().getUserByChannel(ctx.channel());

        if (user != null) {
            Logger.logInfo("Disconnection from ".concat(user.getNetwork().getIpAddress()));
            UserManager.getInstance().getUsers().remove(user);
            user.getNetwork().close();

            if (user.getDetails() != null) {
                user.getDetails().setOnline(false);
                UserManager.getInstance().getUserDetailsDao().save(user.getDetails());
            }

            user.dispose();
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyRequest message) {
        Channel channel = ctx.channel();
        User user = UserManager.getInstance().getUserByChannel(channel);

        MessageHandler.getInstance().handle(user, message);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
}
