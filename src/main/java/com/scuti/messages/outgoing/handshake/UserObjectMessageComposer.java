package com.scuti.messages.outgoing.handshake;

import com.scuti.game.users.components.UserDetails;
import com.scuti.messages.outgoing.MessageComposer;

public class UserObjectMessageComposer extends MessageComposer {
    private UserDetails details;
    public UserObjectMessageComposer(UserDetails details) {
        this.getResponse().setHeader(5);
        this.details = details;
    }

    public UserDetails getDetails() {
        return details;
    }

    @Override
    public void compose() {
        this.getResponse().appendStringWithBreak(Integer.toString(this.getDetails().getId())); // id
        this.getResponse().appendStringWithBreak(this.getDetails().getUsername()); // username
        this.getResponse().appendStringWithBreak(this.getDetails().getFigure()); // look
        this.getResponse().appendStringWithBreak(this.getDetails().getSex()); // gender
        this.getResponse().appendStringWithBreak(this.getDetails().getMotto()); // custom data
        this.getResponse().appendStringWithBreak(this.getDetails().getUsername());

        this.getResponse().appendBoolean(false);

        this.getResponse().appendInt32(10); // respect total
        this.getResponse().appendInt32(10); // respect left
        this.getResponse().appendInt32(10); // pet respect left
        this.getResponse().appendBoolean(false); // stream published allowed
    }
}
