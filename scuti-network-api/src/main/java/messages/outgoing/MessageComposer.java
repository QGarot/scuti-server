package messages.outgoing;

import server.streams.IResponse;

/**
 * A message composer abstraction representing an outgoing message to send to the client.
 */
public abstract class MessageComposer {

    private IResponse response;

    /**
     * Returns response
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