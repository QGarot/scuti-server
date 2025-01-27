package server.connections;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import logger.Logger;
import messages.outgoing.MessageComposer;
import game.users.IUser;

public class Connection implements IConnection {

    private final Channel channel;
    private IUser user;

    public Connection(Channel channel) {
        this.channel = channel;
    }

    @Override
    public ChannelFuture disconnect() {
        if (this.getUser() != null) {
            this.getUser().dispose();
        }

        return this.getChannel().disconnect();
    }

    @Override
    public ChannelFuture close() {
        return this.getChannel().close();
    }

    @Override
    public void send(MessageComposer messageComposer) {
        messageComposer.compose();
        ChannelFuture f = this.getChannel().writeAndFlush(messageComposer.getResponse().getBytes());
        // Log message composer once it is sent
        f.addListener((ChannelFutureListener) _ -> {
            Logger.logOutgoing(messageComposer.getResponse().getHeader());
        });
    }

    @Override
    public String getIpAddress() {
        return this.getChannel().remoteAddress().toString().replace("/", "").split(":")[0];
    }

    @Override
    public Channel getChannel() {
        return this.channel;
    }

    @Override
    public IUser getUser() {
        return this.user;
    }

    @Override
    public void setUser(IUser user) {
        this.user = user;
    }
}
