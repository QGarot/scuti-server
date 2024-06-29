package com.scuti.server.netty.connections;

import com.scuti.game.users.UserManager;
import com.scuti.messages.MessageHandler;
import com.scuti.server.netty.NettyServer;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.util.logger.Logger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Objects;

public class ConnectionHandler extends SimpleChannelInboundHandler<NettyRequest> {

    private final NettyServer server;

    public ConnectionHandler(NettyServer server) {
        this.server = server;
    }

    public NettyServer getServer() {
        return this.server;
    }

    public void registerNettyConnection(NettyConnection connection) {
        // Add connection to the manager
        NettyConnectionManager.getInstance().getConnections().add(connection);
        // Add channel
        this.getServer().getChannels().add(connection.getChannel());
    }

    public void deregisterNettyConnection(NettyConnection connection) {
        // Remove connection from the manager
        NettyConnectionManager.getInstance().getConnections().remove(connection);
        // Remove channel
        this.getServer().getChannels().remove(connection.getChannel());
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        // New netty connection
        this.getServer().incrementConnections();
        NettyConnection newClient = new NettyConnection(ctx.channel(), this.getServer().getConnections());
        ctx.channel().attr(NettyConnection.CONNECTION_ATTRIBUTE_KEY).set(newClient);
        this.registerNettyConnection(newClient);

        // Max connection per ip check
        int maxConnectionPerIp = 2;
        int count = 0;
        for (NettyConnection connection: NettyConnectionManager.getInstance().getConnections()) {
            if (Objects.equals(newClient.getIpAddress(), connection.getIpAddress())) {
                count = count + 1;
            }
        }
        if (count <= maxConnectionPerIp) {
            NettyConnectionManager.getInstance().getConnections().add(newClient);
        } else {
            newClient.disconnect();
            Logger.logInfo("Connection failed from ".concat(newClient.getIpAddress()).concat(". Max connection per ip is ".concat(String.valueOf(maxConnectionPerIp))));
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        this.getServer().decrementConnections();
        // Get netty connection and remove the channel
        NettyConnection connection = ctx.channel().attr(NettyConnection.CONNECTION_ATTRIBUTE_KEY).get();
        this.deregisterNettyConnection(connection);

        // Set user offline and dispose it
        if (connection.getUser() != null) {
            connection.getUser().getDetails().setOnline(false);
            UserManager.getInstance().getUserDetailsDao().save(connection.getUser().getDetails());
            Logger.logInfo("Disconnection: ".concat(connection.getUser().getDetails().getUsername()));
            connection.getUser().dispose();
        } else {
            Logger.logInfo("Disconnection from ".concat(connection.getIpAddress()));
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyRequest message) {
        NettyConnection connection = ctx.channel().attr(NettyConnection.CONNECTION_ATTRIBUTE_KEY).get();
        if (connection != null) {
            MessageHandler.getInstance().handle(connection, message);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
}
