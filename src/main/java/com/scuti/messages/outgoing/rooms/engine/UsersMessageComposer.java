package com.scuti.messages.outgoing.rooms.engine;

import com.scuti.game.users.components.data.UserDetails;
import com.scuti.messages.outgoing.MessageComposer;

public class UsersMessageComposer extends MessageComposer {
    private final UserDetails userDetails;

    public UsersMessageComposer(UserDetails userDetails) {
        this.getResponse().setHeader(28);
        this.userDetails = userDetails;
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
        this.getResponse().appendInt32(1);

        // TODO: work with UserEntity
        this.getResponse().appendInt32(1); // pos x
        this.getResponse().appendInt32(1); // pos y
        this.getResponse().appendStringWithBreak(String.valueOf(5)); // pos z

        this.getResponse().appendInt32(2);
        this.getResponse().appendInt32(1);
        this.getResponse().appendStringWithBreak(this.getUserDetails().getSex());
        this.getResponse().appendInt32(-1);
        this.getResponse().appendInt32(1);
        this.getResponse().appendInt32(-1);
        this.getResponse().appendStringWithBreak("");
        this.getResponse().appendInt32(0);
    }
}
