package com.scuti.game.users;
import com.scuti.server.netty.NettyPlayerNetwork;

public class User {
    private final NettyPlayerNetwork network;
    public User(NettyPlayerNetwork network) {
        this.network = network;
    }

    public NettyPlayerNetwork getNetwork() {
        return this.network;
    }
}
