package com.scuti.api.netty;

import com.scuti.messages.outgoing.MessageComposer;

public interface PlayerNetwork {
    public void send(MessageComposer messageComposer);
    public void sendQueued(MessageComposer messageComposer);
    public void close();
    public void addPipelineStage(Object object);
    public void flush();
}
