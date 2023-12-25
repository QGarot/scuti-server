package com.scuti.game.users;
import com.scuti.game.users.components.UserDetails;
import com.scuti.messages.outgoing.MessageComposer;
import com.scuti.server.netty.NettyPlayerNetwork;
import com.scuti.util.logger.Logger;

public class User {
    private final NettyPlayerNetwork network;
    private final UserDetails details;
    public User(NettyPlayerNetwork network) {
        this.network = network;
        this.details = new UserDetails();
    }

    public UserDetails getDetails() {
        return this.details;
    }

    public NettyPlayerNetwork getNetwork() {
        return this.network;
    }

    public void send(MessageComposer message) {
        try {
            message.compose();
            this.getNetwork().send(message);
        } catch (Exception e) {
            Logger.logError(e.getMessage());
        }
    }
}
