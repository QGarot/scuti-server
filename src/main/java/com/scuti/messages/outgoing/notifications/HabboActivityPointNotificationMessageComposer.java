package com.scuti.messages.outgoing.notifications;

import com.scuti.messages.outgoing.MessageComposer;

public class HabboActivityPointNotificationMessageComposer extends MessageComposer {
    private int pointType;
    private int amount;
    public HabboActivityPointNotificationMessageComposer(int pointType, int amount) {
        this.getResponse().setHeader(438);
        this.pointType = pointType;
        this.amount = amount;
    }

    public int getPointType() {
        return this.pointType;
    }

    public int getAmount() {
        return this.amount;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(this.getAmount());
        this.getResponse().appendInt32(0); // change
        this.getResponse().appendInt32(this.getPointType());
    }
}
