package com.scuti.api.netty;

import com.scuti.messages.outgoing.MessageComposer;

public interface IPlayerNetwork {
    void send(MessageComposer messageComposer);
    void close();
    String getIpAddress();
}
