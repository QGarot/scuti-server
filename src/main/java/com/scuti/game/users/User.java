package com.scuti.game.users;
import com.scuti.game.rooms.Room;
import com.scuti.game.users.components.friendship.Friendship;
import com.scuti.game.users.components.data.UserDetails;
import com.scuti.messages.outgoing.MessageComposer;
import com.scuti.messages.outgoing.handshake.AuthenticationOKMessageComposer;
import com.scuti.messages.outgoing.handshake.DisconnectReasonMessageComposer;
import com.scuti.messages.outgoing.users.MotdNotificationMessageComposer;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.util.logger.Logger;
import io.netty.util.AttributeKey;

import java.util.ArrayList;
import java.util.List;

public class User {
    // Components
    private UserDetails details;
    private Friendship friendship;
    // Rooms
    private List<Room> rooms;
    private int roomId;

    public User(UserDetails details, Friendship friendship) {
        this.details = details;
        this.friendship = friendship;

        // Rooms
        this.rooms = new ArrayList<>();
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomId() {
        return roomId;
    }

    public Friendship getFriendship() {
        return this.friendship;
    }

    public UserDetails getDetails() {
        return this.details;
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
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
