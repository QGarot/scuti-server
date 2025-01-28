package messages.outgoing.notifications;

import messages.outgoing.MessageComposer;
import messages.outgoing.OutgoingHeaders;
import server.streams.Response;

public class HabboActivityPointNotificationMessageComposer extends MessageComposer {

    private final int pointType;
    private final int amount;

    public HabboActivityPointNotificationMessageComposer(int pointType, int amount) {
        this.response = new Response(OutgoingHeaders.HABBO_ACTIVITY_POINT_NOTIFICATION);
        this.pointType = pointType;
        this.amount = amount;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(this.amount);
        this.getResponse().appendInt32(0); // change
        this.getResponse().appendInt32(this.pointType);
    }
}
