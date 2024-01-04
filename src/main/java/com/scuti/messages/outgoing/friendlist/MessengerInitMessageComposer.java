package com.scuti.messages.outgoing.friendlist;

import com.scuti.game.users.components.messenger.Messenger;
import com.scuti.game.users.components.messenger.users.Buddy;
import com.scuti.messages.outgoing.MessageComposer;

public class MessengerInitMessageComposer extends MessageComposer {
    private Messenger messenger;
    public MessengerInitMessageComposer(Messenger messenger) {
        this.getResponse().setHeader(12);
        this.messenger = messenger;
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public void serializeBuddy(Buddy buddy) {
        this.getResponse().appendInt32(buddy.getId()); // id
        this.getResponse().appendStringWithBreak(buddy.getUsername()); // name
        this.getResponse().appendInt32(buddy.getGender()); // gender
        this.getResponse().appendBoolean(!buddy.isOnline()); // online
        this.getResponse().appendBoolean(buddy.isFollowingAllowed()); // following allowed
        this.getResponse().appendStringWithBreak(buddy.getFigure()); // figure
        this.getResponse().appendInt32(buddy.getCategoryId()); // category id
        this.getResponse().appendStringWithBreak(buddy.getMotto()); // motto
        this.getResponse().appendStringWithBreak(buddy.getLastLogin()); // last access
        this.getResponse().appendStringWithBreak(""); // real name
        this.getResponse().appendStringWithBreak(buddy.getFacebookId()); // facebook id
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(6000); // user friend limit
        this.getResponse().appendInt32(200); // normal friend limit
        this.getResponse().appendInt32(6000); // extended friend limit
        this.getResponse().appendInt32(900); // "even more extended friend limit" (lol)

        this.getResponse().appendInt32(0); // number of categories
        // categories
        //this.getResponse().appendInt32(1); //id
        //this.getResponse().appendStringWithBreak("cat1"); //name
        //this.getResponse().appendInt32(2);
        //this.getResponse().appendStringWithBreak("cat2");

        this.getResponse().appendInt32(this.getMessenger().getBuddies().size()); // number of friends..
        // friends
        for (Buddy buddy: this.getMessenger().getBuddies()) {
            this.serializeBuddy(buddy);
        }
    }
}
