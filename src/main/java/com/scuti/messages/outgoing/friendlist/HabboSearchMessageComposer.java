package com.scuti.messages.outgoing.friendlist;

import com.scuti.game.users.friends.Buddy;
import com.scuti.messages.outgoing.MessageComposer;

import java.util.List;

public class HabboSearchMessageComposer extends MessageComposer {
    private List<Buddy> friends;
    private List<Buddy> others;
    public HabboSearchMessageComposer(List<Buddy> friends, List<Buddy> others) {
        this.getResponse().setHeader(435);
        this.friends = friends;
        this.others = others;
    }

    public List<Buddy> getFriends() {
        return friends;
    }

    public List<Buddy> getOthers() {
        return others;
    }

    public void serializeBuddy(Buddy buddy) {
        this.getResponse().appendInt32(buddy.getId()); // id
        this.getResponse().appendStringWithBreak(buddy.getUsername()); // name
        this.getResponse().appendStringWithBreak(buddy.getMotto()); // motto
        this.getResponse().appendBoolean(buddy.isOnline()); // online
        this.getResponse().appendBoolean(buddy.isFollowingAllowed()); // following allowed
        this.getResponse().appendStringWithBreak("");
        this.getResponse().appendInt32(0); // ??
        this.getResponse().appendStringWithBreak(buddy.getFigure()); // figure
        this.getResponse().appendStringWithBreak(buddy.getLastLogin()); // last login
        this.getResponse().appendStringWithBreak(""); // real name
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(this.getFriends().size());
        for (Buddy buddy: this.getFriends()) {
            this.serializeBuddy(buddy);
        }

        this.getResponse().appendInt32(this.getOthers().size());
        for (Buddy buddy: this.getOthers()) {
            this.serializeBuddy(buddy);
        }
    }
}
