package com.scuti.messages.outgoing.friendlist;

import com.scuti.messages.outgoing.MessageComposer;

public class MessengerInitMessageComposer extends MessageComposer {
    public MessengerInitMessageComposer() {
        this.getResponse().setHeader(12);
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(200); // user friend limit
        this.getResponse().appendInt32(200); // normal friend limit
        this.getResponse().appendInt32(200); // extended friend limit
        this.getResponse().appendInt32(200); // "even more extended friend limit" (lol)

        this.getResponse().appendInt32(0);
        // categories
        //this.getResponse().appendInt32(1); //id
        //this.getResponse().appendStringWithBreak("cat1"); //name
        //this.getResponse().appendInt32(2);
        //this.getResponse().appendStringWithBreak("cat2");

        this.getResponse().appendInt32(1); // number of friends..
        // friends
        this.getResponse().appendInt32(1); // id
        this.getResponse().appendStringWithBreak("KOZEN"); // name
        this.getResponse().appendInt32(1); // gender
        this.getResponse().appendBoolean(true); // online
        this.getResponse().appendBoolean(true); // following allowed
        this.getResponse().appendStringWithBreak("hr-115-42.hd-190-1.ch-215-62.lg-285-91.sh-290-62"); // figure
        this.getResponse().appendInt32(1); // category id
        this.getResponse().appendStringWithBreak("Hi Scuti :p"); // motto
        this.getResponse().appendStringWithBreak("27/12/2023"); // last access
        this.getResponse().appendStringWithBreak(""); // real name
        this.getResponse().appendStringWithBreak("facebook"); // facebook id
    }
}
