package server.netty.connections;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import logger.Logger;
import messages.IHandler;
import server.connections.IConnection;
import server.netty.streams.NettyRequest;

import java.util.Objects;

public class ConnectionHandler extends SimpleChannelInboundHandler<NettyRequest> {

    private final NettyConnectionManager connectionManager;

    private final DefaultChannelGroup channels;

    private final IHandler messageHandler;

    public ConnectionHandler(NettyConnectionManager connectionManager, DefaultChannelGroup channels, IHandler messageHandler) {
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

    private void registerNettyConnection(NettyConnection connection) {
        // Add connection to the manager
        this.getConnectionManager().getConnections().add(connection);
        // Add channel
        this.getChannels().add(connection.getChannel());
    }

    private void unregisterNettyConnection(NettyConnection connection) {
        // Remove connection from the manager
        this.getConnectionManager().getConnections().remove(connection);
        // Remove channel
        this.getChannels().remove(connection.getChannel());
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        // New netty connection
        NettyConnection newClient = new NettyConnection(ctx.channel(), this.getConnectionManager().getConnections().size() + 1);
        ctx.channel().attr(NettyConnection.CONNECTION_ATTRIBUTE_KEY).set(newClient);
        this.registerNettyConnection(newClient);

        // Max connection per ip check
        int maxConnectionPerIp = 2;
        int count = 0;

        for (NettyConnection connection: this.getConnectionManager().getConnections()) {
            if (Objects.equals(newClient.getIpAddress(), connection.getIpAddress())) {
                count = count + 1;
            }
        }

        if (count <= maxConnectionPerIp) {
            this.getConnectionManager().getConnections().add(newClient);
        } else {
            newClient.disconnect();
            Logger.logInfo("Connection failed from ".concat(newClient.getIpAddress()).concat(". Max connection per ip is ".concat(String.valueOf(maxConnectionPerIp))));
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        // Get netty connection and remove the channel
        NettyConnection connection = ctx.channel().attr(NettyConnection.CONNECTION_ATTRIBUTE_KEY).get();
        this.unregisterNettyConnection(connection);

        // Set user offline and dispose it
//        if (connection.getUser() != null) {
//            connection.getUser().getDetails().setOnline(false);
//            UserManager.getInstance().getUserDetailsDao().save(connection.getUser().getDetails());
//            Logger.logInfo("Disconnection: ".concat(connection.getUser().getDetails().getUsername()));
//            connection.getUser().dispose();
//        } else {
//            Logger.logInfo("Disconnection from ".concat(connection.getIpAddress()));
//        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyRequest message) {
        NettyConnection connection = ctx.channel().attr(NettyConnection.CONNECTION_ATTRIBUTE_KEY).get();
        if (connection != null) {
            this.getMessageHandler().handle(connection, message);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
}
