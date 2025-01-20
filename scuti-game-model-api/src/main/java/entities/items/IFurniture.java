package entities.items;

import entities.IEntity;

public interface IFurniture extends IEntity {
    /**
     * Returns the id.
     * @return id
     */
    int getId();

    /**
     * Returns the public name.
     * @return public name
     */
    String getPublicName();

    /**
     * Sets the public name.
     * @param publicName: new public name
     */
    void setPublicName(String publicName);

    /**
     * Returns the item name.
     * @return item name
     */
    String getItemName();

    /**
     * Sets the item name.
     * @param itemName: new item name
     */
    void setItemName(String itemName);

    /**
     * Returns the type.
     * @return type
     */
    String getType();

    /**
     * Sets the type.
     * @param type: new type
     */
    void setType(String type);

    /**
     * Returns the width.
     * @return width
     */
    int getWidth();

    /**
     * Sets the width.
     * @param width: new width
     */
    void setWidth(int width);

    /**
     * Returns the length.
     * @return length
     */
    int getLength();

    /**
     * Sets the length.
     * @param length: new length
     */
    void setLength(int length);

    /**
     * Returns the stack height.
     * @return stack height
     */
    int getStackHeight();

    /**
     * Sets the stack height.
     * @param stackHeight: new stack height
     */
    void setStackHeight(int stackHeight);

    /**
     * Returns true if this furniture is stackable.
     * @return stackable
     */
    boolean canStack();

    /**
     * Sets if this furniture can be stacked.
     * @param canStack: new boolean
     */
    void setCanStack(boolean canStack);

    /**
     * Returns true if users can sit on this furniture.
     * @return sit
     */
    boolean canSit();

    /**
     * Sets if players can sit on this furniture.
     * @param canSit: new boolean
     */
    void setCanSit(boolean canSit);

    /**
     * Returns true if this furniture is walkable.
     * @return walkable
     */
    boolean isWalkable();

    /**
     * Sets if players can walk on this furniture.
     * @param walkable: new boolean
     */
    void setWalkable(boolean walkable);

    /**
     * Returns the sprite id.
     * @return sprite id
     */
    int getSpriteId();

    /**
     * Sets the sprite id.
     * @param spriteId: new sprite id
     */
    void setSpriteId(int spriteId);

    /**
     * Returns true if this furniture can be reused.
     * @return recyclable
     */
    boolean canRecycle();

    /**
     * Sets if this furniture can be reused.
     * @param canRecycle: new boolean
     */
    void setCanRecycle(boolean canRecycle);

    /**
     * Returns true if this furniture can be traded.
     * @return trade
     */
    boolean canTrade();

    /**
     * Sets if this furniture can be traded.
     * @param canTrade: new boolean
     */
    void setCanTrade(boolean canTrade);

    /**
     * Returns true if this furniture is sellable on marketplace.
     * @return marketable
     */
    boolean canSellOnMarketplace();

    /**
     * Sets if this furniture is sellable on marketplace.
     * @param canSellOnMarketplace: new boolean
     */
    void setCanSellOnMarketplace(boolean canSellOnMarketplace);

    /**
     * Returns true if this furniture can be offered as a gift.
     * @return gift
     */
    boolean canGift();

    /**
     * Sets if this furniture can be offered as a gift.
     * @param canGift: new boolean
     */
    void setCanGift(boolean canGift);

    /**
     * Returns true if this furniture can be stacked in the player inventory.
     * @return stackable in inventory
     */
    boolean canStackInInventory();

    /**
     * Sets if this furniture can be stacked in the player inventory.
     * @param canStackInInventory: new boolean
     */
    void setCanStackInInventory(boolean canStackInInventory);

    /**
     * Returns the interaction type.
     * @return interaction type
     */
    String getInteractionType();

    /**
     * Sets the interaction type.
     * @param interactionType: new interaction type
     */
    void setInteractionType(String interactionType);

    /**
     * Returns the interaction modes count.
     * @return interaction modes
     */
    int getInteractionModesCount();

    /**
     * Sets the interaction modes count.
     * @param interactionModesCount: new interaction modes count
     */
    void setInteractionModesCount(int interactionModesCount);

    /**
     * Returns the vending ids.
     * @return vending ids
     */
    int getVendingIds();

    /**
     * Sets the vending id.
     * @param vendingIds: new vending id
     */
    void setVendingIds(int vendingIds);

    /**
     * Returns true if this furniture is an arrow.
     * @return arrow
     */
    boolean isArrow();

    /**
     * Sets if this furniture is an arrow.
     * @param arrow: new boolean
     */
    void setArrow(boolean arrow);

    /**
     * Returns true if this furniture has an adjustable height.
     * @return adjustable height
     */
    boolean hasAdjustableHeight();

    /**
     * Sets if this furniture has an adjustable height.
     * @param heightAdjustable: new boolean
     */
    void setHeightAdjustable(boolean heightAdjustable);

    /**
     * Returns the male effect id.
     * @return effect id
     */
    int getMaleEffect();

    /**
     * Sets the male effect.
     * @param maleEffect: new male effect
     */
    void setMaleEffect(int maleEffect);

    /**
     * Returns the female effect id.
     * @return effect id
     */
    int getFemaleEffect();

    /**
     * Sets the female effect.
     * @param femaleEffect: new female effect
     */
    void setFemaleEffect(int femaleEffect);
}
