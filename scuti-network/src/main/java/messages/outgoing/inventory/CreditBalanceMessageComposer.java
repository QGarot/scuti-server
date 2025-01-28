package messages.outgoing.inventory;

import messages.outgoing.MessageComposer;
import messages.outgoing.OutgoingHeaders;
import server.streams.Response;

public class CreditBalanceMessageComposer extends MessageComposer {

    private final int credits;

    public CreditBalanceMessageComposer(int credits) {
        this.credits = credits;
        this.response = new Response(OutgoingHeaders.CREDIT_BALANCE);
    }

    @Override
    public void compose() {
        this.getResponse().appendStringWithBreak(Integer.toString(this.credits));
    }
}
