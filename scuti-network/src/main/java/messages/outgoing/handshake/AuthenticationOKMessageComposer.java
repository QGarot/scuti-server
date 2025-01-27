package messages.outgoing.handshake;

import messages.outgoing.MessageComposer;
import messages.outgoing.OutgoingHeaders;
import server.streams.Response;

public class AuthenticationOKMessageComposer extends MessageComposer {

    public AuthenticationOKMessageComposer() {
        this.response = new Response(OutgoingHeaders.AUTHENTICATION_OK);
    }

    @Override
    public void compose() {
        // Nothing to compose here
    }
}
