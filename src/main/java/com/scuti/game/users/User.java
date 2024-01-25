package com.scuti.game.users;
import com.scuti.game.rooms.Room;
import com.scuti.game.users.components.messenger.Messenger;
import com.scuti.game.users.components.data.UserDetails;
import com.scuti.messages.outgoing.MessageComposer;
import com.scuti.messages.outgoing.handshake.DisconnectReasonMessageComposer;
import com.scuti.server.netty.NettyPlayerNetwork;
import com.scuti.util.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final NettyPlayerNetwork network;
    private UserDetails details;
    private Messenger messenger;
    private final List<Room> rooms;
    private int roomId;

    public User(NettyPlayerNetwork network) {
        this.network = network;

        // Components
        this.details = new UserDetails();
        this.messenger = new Messenger();

        // Rooms
        this.rooms = new ArrayList<>();
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomId() {
        return roomId;
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

    public void dispose() {
        this.details = null;

        this.getMessenger().dispose();
        this.messenger = null;

    }

    public void disconnect() {
        this.getDetails().setOnline(false);
        this.getNetwork().close();
        this.send(new DisconnectReasonMessageComposer(1));
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
