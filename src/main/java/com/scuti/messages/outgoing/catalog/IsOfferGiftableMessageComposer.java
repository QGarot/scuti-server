package com.scuti.messages.outgoing.catalog;

import com.scuti.messages.outgoing.MessageComposer;

public class IsOfferGiftableMessageComposer extends MessageComposer {
    private final int itemId;
    private final boolean allowGift;
    public IsOfferGiftableMessageComposer(int itemId, boolean allowGift) {
        this.getResponse().setHeader(622);
        this.itemId = itemId;
        this.allowGift = allowGift;
    }

    public int getItemId() {
        return this.itemId;
    }

    public boolean allowGift() {
        return this.allowGift;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(this.getItemId());
        this.getResponse().appendBoolean(this.allowGift());
    }
}
