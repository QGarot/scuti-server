package entities.catalog;

import entities.IEntity;

public interface ICatalogItem extends IEntity {
    /**
     * Returns the id.
     * @return id
     */
    int getId();

    /**
     * Returns the item id.
     * @return item id
     */
    int getItemId();

    /**
     * Sets the item id.
     * @param itemId: new item id
     */
    void setItemId(int itemId);

    /**
     * Returns the name.
     * @return name
     */
    String getName();

    /**
     * Sets the name.
     * @param name: new item name
     */
    void setName(String name);

    /**
     * Returns the catalog item credits cost.
     * @return credits cost
     */
    int getCreditsCost();

    /**
     * Sets the credits cost.
     * @param creditsCost: new item cost
     */
    void setCreditsCost(int creditsCost);

    /**
     * Returns the catalog item points cost.
     * @return points cost
     */
    int getPointsCost();

    /**
     * Sets the points cost.
     * @param pointsCost: new item cost
     */
    void setPointsCost(int pointsCost);

    /**
     * Returns the point type.
     * @return point type
     */
    int getPointType();

    /**
     * Sets the point type.
     * @param pointType: new item point type
     */
    void setPointType(int pointType);

    /**
     * Returns the amount of item(s).
     * @return amount
     */
    int getAmount();

    /**
     * Sets the amount.
     * @param amount: new amount
     */
    void setAmount(int amount);

    /**
     * Returns the page id corresponding.
     * @return page id
     */
    int getPageId();

    /**
     * Sets the page id.
     * @param pageId: new page id
     */
    void setPageId(int pageId);

    /**
     * Returns whether the vip is needed.
     * @return vip needed
     */
    int getVip();

    /**
     * Sets the vip.
     * @param vip: new vip
     */
    void setVip(int vip);

    /**
     * Returns the achievement.
     * @return achievement
     */
    int getAchievement();

    /**
     * Sets the achievement.
     * @param achievement: new achievement
     */
    void setAchievement(int achievement);

    /**
     * Returns the song id.
     * @return song id
     */
    int getSongId();

    /**
     * Sets the song id.
     * @param songId: new song id
     */
    void setSongId(int songId);
}
