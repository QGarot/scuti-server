package com.scuti.game.users;
import com.scuti.game.users.components.Messenger;
import com.scuti.game.users.components.UserDetails;
import com.scuti.messages.outgoing.MessageComposer;
import com.scuti.messages.outgoing.handshake.DisconnectReasonMessageComposer;
import com.scuti.server.netty.NettyPlayerNetwork;
import com.scuti.util.logger.Logger;

public class User {
    private final NettyPlayerNetwork network;
    private final UserDetails details;
    private final Messenger messenger;
    public User(NettyPlayerNetwork network) {
        this.network = network;

        // Components
        this.details = new UserDetails();
        this.messenger = new Messenger();
    }

    public void login() {
        this.getDetails().setOnline(true);
    }

    public Messenger getMessenger() {
        return this.messenger;
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

    public void disconnect() {
        this.getNetwork().close();
        this.send(new DisconnectReasonMessageComposer(1));
    }
}
