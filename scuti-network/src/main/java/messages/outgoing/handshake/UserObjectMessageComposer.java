package messages.outgoing.handshake;

import entities.users.IUserDetails;
import messages.outgoing.MessageComposer;
import messages.outgoing.OutgoingHeaders;
import server.streams.Response;

public class UserObjectMessageComposer extends MessageComposer {

    // User details that has to be sent
    private final IUserDetails details;

    public UserObjectMessageComposer(IUserDetails details) {
        this.response = new Response(OutgoingHeaders.USER_OBJECT);
        this.details = details;
    }

    @Override
    public void compose() {
        this.getResponse().appendStringWithBreak(Integer.toString(this.details.getId())); // id
        this.getResponse().appendStringWithBreak(this.details.getUsername()); // username
        this.getResponse().appendStringWithBreak(this.details.getFigure()); // look
        this.getResponse().appendStringWithBreak(this.details.getSex()); // gender
        this.getResponse().appendStringWithBreak(this.details.getMotto()); // custom data
        this.getResponse().appendStringWithBreak(this.details.getUsername());

        this.getResponse().appendBoolean(false);

        this.getResponse().appendInt32(10); // respect total
        this.getResponse().appendInt32(10); // respect left
        this.getResponse().appendInt32(10); // pet respect left
        this.getResponse().appendBoolean(false); // stream published allowed
    }
}
