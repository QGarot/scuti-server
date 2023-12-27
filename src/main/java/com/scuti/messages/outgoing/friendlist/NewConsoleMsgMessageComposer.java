package com.scuti.messages.outgoing.friendlist;

import com.scuti.messages.outgoing.MessageComposer;

public class NewConsoleMsgMessageComposer extends MessageComposer {
    private int senderId;
    private String message;
    public NewConsoleMsgMessageComposer(int senderId, String message) {
        this.getResponse().setHeader(134);
        this.senderId = senderId;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getSenderId() {
        return senderId;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(this.getSenderId());
        this.getResponse().appendStringWithBreak(this.getMessage());
    }
}
