package com.scuti.messages.outgoing.friendlist;

import com.scuti.messages.outgoing.MessageComposer;

public class NewBuddyRequestMessageComposer extends MessageComposer {
    private final int requestId;
    private final String requesterName;
    private final String requesterFigure;
    public NewBuddyRequestMessageComposer(int requestId, String requesterName, String requesterFigure) {
        this.getResponse().setHeader(132);
        this.requestId = requestId;
        this.requesterName = requesterName;
        this.requesterFigure = requesterFigure;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public String getRequesterFigure() {
        return requesterFigure;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(this.getRequestId()); // request ID
        this.getResponse().appendStringWithBreak(this.getRequesterName()); // requester name
        this.getResponse().appendStringWithBreak(this.getRequesterFigure()); // figure string
    }
}
