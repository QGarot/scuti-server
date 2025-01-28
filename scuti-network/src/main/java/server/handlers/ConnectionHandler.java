package server.handlers;

import io.netty.channel.*;
import logger.Logger;
import server.connections.Connection;
import server.connections.IConnection;
import server.streams.IRequest;

import java.util.HashMap;
import java.util.Objects;

public class ConnectionHandler extends SimpleChannelInboundHandler<IRequest> {

    private final IGameHandler messageHandler; // connection handler needs to know message handler to handle game packets
    private final HashMap<ChannelId, IConnection> connections;

    public ConnectionHandler(IGameHandler messageHandler) {
        this.connections = new HashMap<>();
        this.messageHandler = messageHandler;
    }

    /**
     * Returns the set of connections.
     * @return connections established with the client side
     */
    private HashMap<ChannelId, IConnection> getConnections() {
        return this.connections;
    }

    /**
     * Checks if the number of connections is not over the norm.
     * @param attempt: attempted connection
     * @return true if the connection is allowed
     */
    private boolean checkMaxConnectionPerIp(IConnection attempt) {
        // Max connections per ip allowed
        int max = 2;

        int counter = 0;
        for (IConnection connection: this.getConnections().values()) {
            if (Objects.equals(attempt.getIpAddress(), connection.getIpAddress())) {
                counter = counter + 1;
            }
        }

        return counter <= max;
    }

    /**
     * Returns the game message handler.
     * @return message handler
     */
    private IGameHandler getMessageHandler() {
        return this.messageHandler;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        IConnection connection = new Connection(ctx.channel());

        if (this.checkMaxConnectionPerIp(connection)) {
            this.getConnections().put(connection.getChannel().id(), connection);
        } else {
            ChannelFuture f = connection.close();
            f.addListener((ChannelFutureListener) channelFuture -> {
                Logger.logInfo("Too much connection with the ip: ".concat(connection.getIpAddress()));
            });
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        IConnection connection = this.getConnections().get(ctx.channel().id());
        if (connection != null) {
            if (connection.getUser() != null) {
                // Disconnect the client, close the channel and then delete the connection from the hashmap
                connection.disconnect().addListener((ChannelFutureListener) channelFuture -> {
                    connection.close().addListener((ChannelFutureListener) channelFuture1 -> {
                        this.getConnections().remove(connection.getChannel().id());
                    });
                });
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, IRequest request) throws Exception {
        IConnection connection = this.getConnections().get(channelHandlerContext.channel().id());
        if (connection == null) {
            Logger.logInfo("[ConnectionHandler] No connection found with such a channel id.");
        } else {
            this.getMessageHandler().handle(connection, request);
        }
    }
}
