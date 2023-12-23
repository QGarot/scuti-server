package com.scuti.server.netty;

import com.scuti.api.netty.PlayerNetwork;
import com.scuti.api.messages.MessageComposer;
import io.netty.channel.Channel;

public class NettyPlayerNetwork implements PlayerNetwork {
    private final Channel channel;
    private int connectionId;

    public NettyPlayerNetwork(Channel channel, int connectionId) {
        this.connectionId = connectionId;
        this.channel = channel;
    }

    public Channel getChannel() {
        return this.channel;
    }

    @Override
    public void addPipelineStage(Object object) {

    }

    @Override
    public void close() {
        this.channel.close();
    }

    @Override
    public void send(MessageComposer response) {
        this.channel.writeAndFlush(response);
    }

    @Override
    public void sendQueued(MessageComposer response) {
        this.channel.write(response);
    }

    @Override
    public void flush() {
        this.channel.flush();
    }
}
