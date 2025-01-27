package messages.outgoing;

import server.streams.IResponse;

/**
 * A message composer abstraction representing an outgoing message to send to the client.
 */
public abstract class MessageComposer {

    // The response attribute needs to be visible by all subclasses for specific instantiation:
    // Each MessageComposer is associated with a unique instantiation with a specific header.
    // e.g. MotdNotificationMessageComposer -> a response is created with the header 810.
    protected IResponse response;

    /**
     * Returns response.
     * @return response instance
     */
    public IResponse getResponse() {
        return this.response;
    }

    /**
     * Writes data in the writable object implementing IResponse.
     */
    public abstract void compose();
}