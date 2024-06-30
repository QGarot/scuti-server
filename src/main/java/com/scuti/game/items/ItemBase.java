package com.scuti.game.items;

public class ItemBase {
    private int id;
    private String publicName;
    private String itemName;
    private String type;
    private int width;
    private int length;
    private int stackHeight;
    private boolean canStack;
    private boolean canSit;
    private boolean isWalkable;
    private int spriteId;
    private boolean allowRecycle;
    private boolean allowTrade;
    private boolean allowMarketplaceSell;
    private boolean allowGift;
    private boolean allowInventoryStack;
    private String interactionType;
    private int interactionModesCount;
    private int vendingIds;
    private boolean isArrow;
    private boolean heightAdjustable;
    private int effectMale;
    private int effectFemale;

    public ItemBase(int id, String publicName, String itemName, String type, int width, int length, int stackHeight, boolean canStack, boolean canSit, boolean isWalkable, int spriteId, boolean allowRecycle, boolean allowTrade, boolean allowMarketplaceSell, boolean allowGift, boolean allowInventoryStack, String interactionType, int interactionModesCount, int vendingIds, boolean isArrow, boolean heightAdjustable, int effectMale, int effectFemale) {
        this.id = id;
        this.publicName = publicName;
        this.itemName = itemName;
        this.type = type;
        this.width = width;
        this.length = length;
        this.stackHeight = stackHeight;
        this.canStack = canStack;
        this.canSit = canSit;
        this.isWalkable = isWalkable;
        this.spriteId = spriteId;
        this.allowRecycle = allowRecycle;
        this.allowTrade = allowTrade;
        this.allowMarketplaceSell = allowMarketplaceSell;
        this.allowGift = allowGift;
        this.allowInventoryStack = allowInventoryStack;
        this.interactionType = interactionType;
        this.interactionModesCount = interactionModesCount;
        this.vendingIds = vendingIds;
        this.isArrow = isArrow;
        this.heightAdjustable = heightAdjustable;
        this.effectMale = effectMale;
        this.effectFemale = effectFemale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStackHeight() {
        return stackHeight;
    }

    public void setStackHeight(int stackHeight) {
        this.stackHeight = stackHeight;
    }

    public boolean isCanStack() {
        return canStack;
    }

    public void setCanStack(boolean canStack) {
        this.canStack = canStack;
    }

    public boolean isCanSit() {
        return canSit;
    }

    public void setCanSit(boolean canSit) {
        this.canSit = canSit;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public void setWalkable(boolean walkable) {
        isWalkable = walkable;
    }

    public int getSpriteId() {
        return spriteId;
    }

    public void setSpriteId(int spriteId) {
        this.spriteId = spriteId;
    }

    public boolean isAllowRecycle() {
        return allowRecycle;
    }

    public void setAllowRecycle(boolean allowRecycle) {
        this.allowRecycle = allowRecycle;
    }

    public boolean isAllowTrade() {
        return allowTrade;
    }

    public void setAllowTrade(boolean allowTrade) {
        this.allowTrade = allowTrade;
    }

    public boolean isAllowMarketplaceSell() {
        return allowMarketplaceSell;
    }

    public void setAllowMarketplaceSell(boolean allowMarketplaceSell) {
        this.allowMarketplaceSell = allowMarketplaceSell;
    }

    public boolean allowGift() {
        return allowGift;
    }

    public void setAllowGift(boolean allowGift) {
        this.allowGift = allowGift;
    }

    public boolean isAllowInventoryStack() {
        return allowInventoryStack;
    }

    public void setAllowInventoryStack(boolean allowInventoryStack) {
        this.allowInventoryStack = allowInventoryStack;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }

    public int getInteractionModesCount() {
        return interactionModesCount;
    }

    public void setInteractionModesCount(int interactionModesCount) {
        this.interactionModesCount = interactionModesCount;
    }

    public int getVendingIds() {
        return vendingIds;
    }

    public void setVendingIds(int vendingIds) {
        this.vendingIds = vendingIds;
    }

    public boolean isArrow() {
        return isArrow;
    }

    public void setArrow(boolean arrow) {
        isArrow = arrow;
    }

    public boolean isHeightAdjustable() {
        return heightAdjustable;
    }

    public void setHeightAdjustable(boolean heightAdjustable) {
        this.heightAdjustable = heightAdjustable;
    }

    public int getEffectMale() {
        return effectMale;
    }

    public void setEffectMale(int effectMale) {
        this.effectMale = effectMale;
    }

    public int getEffectFemale() {
        return effectFemale;
    }

    public void setEffectFemale(int effectFemale) {
        this.effectFemale = effectFemale;
    }
}
