package com.scuti.users;

import com.scuti.api.netty.PlayerNetwork;

public class User {
    private final PlayerNetwork network;
    public User(PlayerNetwork network) {
        this.network = network;
    }

    public PlayerNetwork getNetwork() {
        return network;
    }
}
