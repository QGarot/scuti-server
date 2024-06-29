package com.scuti.server.netty.connections;

import com.scuti.api.utils.IManager;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NettyConnectionManager implements IManager {

    private static NettyConnectionManager instance;
    private List<NettyConnection> connections;

    public NettyConnectionManager() {
        this.initialize();
    }

    public static NettyConnectionManager getInstance() {
        if (instance == null) {
            instance = new NettyConnectionManager();
        }

        return instance;
    }

    @Override
    public void initialize() {
        this.connections = new ArrayList<>();
    }

    @Override
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
        for (NettyConnection connection: this.getConnections()) {
            if (connection.getUser() != null && Objects.equals(connection.getUser().getDetails().getUsername(), username)) {
                return connection;
            }
        }

        return null;
    }

    public List<NettyConnection> getConnections() {
        return this.connections;
    }
}
