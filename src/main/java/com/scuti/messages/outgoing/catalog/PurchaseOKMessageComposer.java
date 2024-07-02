package com.scuti.messages.outgoing.catalog;

import com.scuti.messages.outgoing.MessageComposer;

public class PurchaseOKMessageComposer extends MessageComposer {

    private final int offerId;
    private final String localization;
    private final int priceInCredits;
    private final int priceInActivityPoints;
    private final int activityPointType;
    private final String productType;
    private final int furniClassId;
    private final String extraParam;
    private final int productCount;
    private final int expiration;
    private final int clubLevel;

    public PurchaseOKMessageComposer(int offerId, String localization, int priceInCredits, int priceInActivityPoints, int activityPointType, String productType, int furniClassId, String extraParam, int productCount, int expiration, int clubLevel) {
        this.getResponse().setHeader(67);
        this.offerId = offerId;
        this.localization = localization;
        this.priceInCredits = priceInCredits;
        this.priceInActivityPoints = priceInActivityPoints;
        this.activityPointType = activityPointType;
        this.productType = productType;
        this.furniClassId = furniClassId;
        this.extraParam = extraParam;
        this.productCount = productCount;
        this.expiration = expiration;
        this.clubLevel = clubLevel;
    }

    public int getOfferId() {
        return offerId;
    }

    public String getLocalization() {
        return localization;
    }

    public int getPriceInCredits() {
        return priceInCredits;
    }

    public int getPriceInActivityPoints() {
        return priceInActivityPoints;
    }

    public int getActivityPointType() {
        return activityPointType;
    }

    public String getProductType() {
        return productType;
    }

    public int getFurniClassId() {
        return furniClassId;
    }

    public String getExtraParam() {
        return extraParam;
    }

    public int getProductCount() {
        return productCount;
    }

    public int getExpiration() {
        return expiration;
    }

    public int getClubLevel() {
        return clubLevel;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(this.getOfferId());
        this.getResponse().appendStringWithBreak(this.getLocalization());
        this.getResponse().appendInt32(this.getPriceInCredits());
        this.getResponse().appendInt32(this.getPriceInActivityPoints());
        this.getResponse().appendInt32(this.getActivityPointType());
        this.getResponse().appendInt32(1);

        this.getResponse().appendStringWithBreak(this.getProductType().toLowerCase());
        this.getResponse().appendInt32(this.getFurniClassId());
        this.getResponse().appendStringWithBreak(this.getExtraParam());
        this.getResponse().appendInt32(this.getProductCount());
        this.getResponse().appendInt32(this.getExpiration());
        this.getResponse().appendInt32(this.getClubLevel());
    }
}
