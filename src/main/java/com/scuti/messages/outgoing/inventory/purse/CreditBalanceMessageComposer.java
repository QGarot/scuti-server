package com.scuti.messages.outgoing.inventory.purse;

import com.scuti.messages.outgoing.MessageComposer;

public class CreditBalanceMessageComposer extends MessageComposer {
    private int amount;
    public CreditBalanceMessageComposer(int amount) {
        this.getResponse().setHeader(6);
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    @Override
    public void compose() {
        this.getResponse().appendStringWithBreak(Integer.toString(this.getAmount()));
    }
}
