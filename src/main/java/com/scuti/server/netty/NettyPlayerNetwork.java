package com.scuti.server.netty;

import com.scuti.api.netty.IPlayerNetwork;
import com.scuti.messages.outgoing.MessageComposer;
import com.scuti.util.logger.Logger;
import io.netty.channel.Channel;

public class NettyPlayerNetwork implements IPlayerNetwork {
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
    public void close() {
        this.channel.close();
    }

    @Override
    public void send(MessageComposer messageComposer) {
        this.channel.writeAndFlush(messageComposer.getResponse().getBytes());
        Logger.logOutgoing(messageComposer.getResponse().getHeader());
    }

    @Override
    public String getIpAddress() {
        return this.getChannel().remoteAddress().toString().replace("/", "").split(":")[0];
    }
}
