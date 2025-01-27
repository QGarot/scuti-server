package messages.outgoing.handshake;

import messages.outgoing.MessageComposer;
import messages.outgoing.OutgoingHeaders;
import server.streams.Response;

public class SessionParamsMessageComposer extends MessageComposer {

    public SessionParamsMessageComposer() {
        this.response = new Response(OutgoingHeaders.SESSION_PARAM);
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(0);
    }
}
