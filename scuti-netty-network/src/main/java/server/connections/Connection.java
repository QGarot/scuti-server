package server.connections;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import messages.outgoing.MessageComposer;
import services.users.IUser;

public class Connection implements IConnection {

    private final Channel channel;

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

    }

    @Override
    public String getIpAddress() {
        return "";
    }

    @Override
    public Channel getChannel() {
        return this.channel;
    }

    @Override
    public IUser getUser() {
        return null;
    }
}
