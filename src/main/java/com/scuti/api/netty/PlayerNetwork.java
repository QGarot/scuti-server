package com.scuti.api.netty;

import com.scuti.api.messages.MessageComposer;

public interface PlayerNetwork {
    public void send(MessageComposer response);
    public void sendQueued(MessageComposer response);
    public void close();
    public void addPipelineStage(Object object);
    public void flush();
}
