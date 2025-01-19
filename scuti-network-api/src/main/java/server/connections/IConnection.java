package server.connections;

import io.netty.channel.Channel;

/**
 * An interface representing the client.
 * It represents the connection made with the server.
 */
public interface IConnection {
    /**
     * Disconnects the client.
     */
    void disconnect();

    /**
     * Sends a message composer (packet) to the client.
     */
    void send();

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
     * TODO: returns user object...
     * Returns the client's user instance.
     * @return user instance
     */
    int getUser();
}
