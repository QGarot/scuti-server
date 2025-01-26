package server.connections;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import messages.outgoing.MessageComposer;
import services.users.IUser;

/**
 * An interface representing the client.
 * It represents the connection made with the server.
 */
public interface IConnection {
    /**
     * Disconnects the client.
     * @return channel future
     */
    ChannelFuture disconnect();

    /**
     * Closes the client channel.
     * @return channel future
     */
    ChannelFuture close();
    /**
     * Sends a message composer (packet) to the client.
     * @param messageComposer: message to compose
     */
    void send(MessageComposer messageComposer);

    /**
     * Returns the client's ip address.
     * @return ip address
     */
    String getIpAddress();

    /**
     * Returns the client's channel.
     * @return channel
     */
    Channel getChannel();

    /**
     * Returns the client's user instance.
     * @return user instance
     */
    IUser getUser();
}
