package server.netty.connections;

import com.scuti.api.netty.IPlayerNetwork;
import com.scuti.game.users.User;
import com.scuti.messages.outgoing.MessageComposer;
import com.scuti.messages.outgoing.handshake.DisconnectReasonMessageComposer;
import com.scuti.util.logger.Logger;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import server.connections.IConnection;

public class NettyConnection implements IConnection {
    public static final AttributeKey<NettyConnection> CONNECTION_ATTRIBUTE_KEY = AttributeKey.valueOf("NettyConnection");
    private final Channel channel;
    private final int connectionId;
    private User user;

    public NettyConnection(Channel channel, int connectionId) {
        this.connectionId = connectionId;
        this.channel = channel;
    }


    @Override
    public Channel getChannel() {
        return this.channel;
    }

    /**
     * Get connection id
     */
    public int getConnectionId() {
        return this.connectionId;
    }

    @Override
    public int getUser() {
//        return this.user;
    }

    /**
     * Set user associated to this player network
     */
//    public void setUser(User user) {
//        this.user = user;
//    }

    /**
     * Disconnect player network
     */
    @Override
    public void disconnect() {
//        this.send(new DisconnectReasonMessageComposer(1));
        this.getChannel().close();
        this.getChannel().disconnect();
    }

//    @Override
//    public void send(MessageComposer messageComposer) {
//        messageComposer.compose();
//        this.getChannel().writeAndFlush(messageComposer.getResponse().getBytes());
//        Logger.logOutgoing(messageComposer.getResponse().getHeader());
//    }

    @Override
    public String getIpAddress() {
        return this.getChannel().remoteAddress().toString().replace("/", "").split(":")[0];
    }
}
