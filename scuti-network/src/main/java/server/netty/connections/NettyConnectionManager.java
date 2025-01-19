package server.netty.connections;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A class that manages all client connected to the server.
 */
public class NettyConnectionManager {

    private final List<NettyConnection> connections;

    public NettyConnectionManager() {
        this.connections = new ArrayList<>();
    }


    public void unload() {
        for (NettyConnection connection: this.getConnections()) {
            connection.disconnect();
        }
    }

    public NettyConnection getNettyConnection(Channel channel) {
        for (NettyConnection connection: this.getConnections()) {
            if (connection.getChannel() == channel) {
                return connection;
            }
        }

        return null;
    }

    public NettyConnection getNettyConnection(String username) {
//        for (NettyConnection connection: this.getConnections()) {
//            if (connection.getUser() != null && Objects.equals(connection.getUser().getDetails().getUsername(), username)) {
//                return connection;
//            }
//        }
//
        return null;
    }

    public List<NettyConnection> getConnections() {
        return this.connections;
    }
}
