package com.scuti.game.catalog;

public class CatalogItem {
    private int id;
    private int itemId;
    private String name;
    private int creditsCost;
    private int pointsCost;
    private int pointType;
    private int amount;
    private int pageId;
    private int vip;
    private int achievement;
    private int songId;

    public CatalogItem(int id, int itemId, String name, int creditsCost, int pointsCost, int pointType, int amount, int pageId, int vip, int achievement, int songId) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.creditsCost = creditsCost;
        this.pointsCost = pointsCost;
        this.pointType = pointType;
        this.amount = amount;
        this.pageId = pageId;
        this.vip = vip;
        this.achievement = achievement;
        this.songId = songId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditsCost() {
        return creditsCost;
    }

    public void setCreditsCost(int creditsCost) {
        this.creditsCost = creditsCost;
    }

    public int getPointsCost() {
        return pointsCost;
    }

    public void setPointsCost(int pointsCost) {
        this.pointsCost = pointsCost;
    }

    public int getPointType() {
        return pointType;
    }

    public void setPointType(int pointType) {
        this.pointType = pointType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getAchievement() {
        return achievement;
    }

    public void setAchievement(int achievement) {
        this.achievement = achievement;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }
}
