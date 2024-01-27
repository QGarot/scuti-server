package com.scuti.messages.outgoing.rooms.engine;

import com.scuti.game.rooms.entities.RoomUser;
import com.scuti.game.users.components.data.UserDetails;
import com.scuti.messages.outgoing.MessageComposer;

public class UsersMessageComposer extends MessageComposer {
    private final UserDetails userDetails;
    private final RoomUser roomUser;

    public UsersMessageComposer(UserDetails userDetails, RoomUser roomUser) {
        this.getResponse().setHeader(28);
        this.userDetails = userDetails;
        this.roomUser = roomUser;
    }

    public RoomUser getRoomUser() {
        return roomUser;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(1);

        this.getResponse().appendInt32(this.getUserDetails().getId());
        this.getResponse().appendStringWithBreak(this.getUserDetails().getUsername());
        this.getResponse().appendStringWithBreak(this.getUserDetails().getMotto());
        this.getResponse().appendStringWithBreak(this.getUserDetails().getFigure());
        this.getResponse().appendInt32(this.getRoomUser().getVirtualId());

        // TODO: work with UserEntity
        this.getResponse().appendInt32(this.getRoomUser().getPosition().getX());
        this.getResponse().appendInt32(this.getRoomUser().getPosition().getY());
        this.getResponse().appendStringWithBreak(String.valueOf(this.getRoomUser().getPosition().getZ()));

        this.getResponse().appendInt32(2);
        this.getResponse().appendInt32(1);
        this.getResponse().appendStringWithBreak(this.getUserDetails().getSex().toLowerCase());
        this.getResponse().appendInt32(-1);
        this.getResponse().appendInt32(1);
        this.getResponse().appendInt32(-1);
        this.getResponse().appendStringWithBreak("");
        this.getResponse().appendInt32(0);
    }
}
