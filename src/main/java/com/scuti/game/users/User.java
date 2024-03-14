package com.scuti.game.users;
import com.scuti.game.rooms.Room;
import com.scuti.game.users.components.friendship.Friendship;
import com.scuti.game.users.components.data.UserDetails;
import com.scuti.messages.outgoing.MessageComposer;
import com.scuti.messages.outgoing.handshake.AuthenticationOKMessageComposer;
import com.scuti.messages.outgoing.handshake.DisconnectReasonMessageComposer;
import com.scuti.messages.outgoing.users.MotdNotificationMessageComposer;
import com.scuti.server.netty.NettyPlayerNetwork;
import com.scuti.util.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class User {
    // Connection
    private int id;
    private NettyPlayerNetwork network;
    // Components
    private UserDetails details;
    private Friendship friendship;
    // Rooms
    private List<Room> rooms;
    private int roomId;

    public User(NettyPlayerNetwork network) {
        this.network = network;

        // Rooms
        this.rooms = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomId() {
        return roomId;
    }

    /**
     * Initialize all loaded user components and made the connection to the client
     * @param details:
     * @param friendship:
     */
    public void login(UserDetails details, Friendship friendship) {
        // Init components
        this.details = details;
        this.friendship = friendship;

        // TODO: first connection?
        this.send(new AuthenticationOKMessageComposer());
        this.send(new MotdNotificationMessageComposer());
        this.getDetails().setOnline(true);
    }

    public Friendship getFriendship() {
        return this.friendship;
    }

    public UserDetails getDetails() {
        return this.details;
    }

    public NettyPlayerNetwork getNetwork() {
        return this.network;
    }

    /**
     * Compose and send a message composer to the client
     * @param message: message composer to send
     */
    public void send(MessageComposer message) {
        try {
            message.compose();
            this.getNetwork().send(message);
        } catch (Exception e) {
            Logger.logError(e.getMessage());
        }
    }

    public void dispose() {
        if (this.getDetails() != null) {
            this.getDetails().dispose();
            this.details = null;
        }

        if (this.getFriendship() != null) {
            this.getFriendship().dispose();
            this.friendship = null;
        }


        this.getRooms().clear();
        this.rooms = null;

        this.setRoomId(0);
        this.network = null;
    }

    /**
     * Send a packet to disconnect the user
     * @param reason: disconnection reason
     */
    public void disconnect(int reason) {
        this.send(new DisconnectReasonMessageComposer(reason));
        //this.getNetwork().close();
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
