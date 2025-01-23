package entities.rooms;

import entities.IEntity;

public interface IRoomDetails extends IEntity {
    /**
     * Returns the id.
     * @return id
     */
    int getId();

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
     * Returns the caption.
     * @return caption
     */
    String getCaption();

    /**
     * Sets the caption.
     * @param caption: new caption
     */
    void setCaption(String caption);

    /**
     * Returns the owner name.
     * @return owner name
     */
    String getOwnerName();

    /**
     * Sets the owner name.
     * @param ownerName: username of the new owner
     */
    void setOwnerName(String ownerName);

    /**
     * Returns the description.
     * @return description
     */
    String getDescription();

    /**
     * Sets the description.
     * @param description: new description
     */
    void setDescription(String description);

    /**
     * Returns the category.
     * @return category
     */
    int getCategory();

    /**
     * Sets the category.
     * @param category: new category
     */
    void setCategory(int category);

    /**
     * Returns the state.
     * @return state
     */
    int getState();

    /**
     * Sets the state.
     * @param state: new state
     */
    void setState(String state);

    /**
     * Returns the users now.
     * @return number of users in this room
     */
    int getUsersNow();

    /**
     * Sets the number of users in this room.
     * @param usersNow: new number of users in this room
     */
    void setUsersNow(int usersNow);

    /**
     * Returns the users max.
     * @return max capacity
     */
    int getUsersMax();

    /**
     * Sets the capacity.
     * @param usersMax: new capacity
     */
    void setUsersMax(int usersMax);

    /**
     * Returns the model name.
     * @return model name
     */
    String getModelName();

    /**
     * Sets the model name.
     * @param modelName: new model name
     */
    void setModelName(String modelName);

    /**
     * Returns the score.
     * @return score
     */
    int getScore();

    /**
     * Sets the score.
     * @param score: new score
     */
    void setScore(int score);

    /**
     * Returns the tags.
     * @return tags
     */
    String getTags();

    /**
     * Sets the tags.
     * @param tags: new tags
     */
    void setTags(String tags);

    /**
     * Returns the password.
     * @return password
     */
    String getPassword();

    /**
     * Sets the password.
     * @param password: new password
     */
    void setPassword(String password);

    /**
     * Returns the wallpaper id.
     * @return wallpaper id
     */
    String getWallpaper();

    /**
     * Sets the wallpaper id.
     * @param wallpaper: new wallpaper id
     */
    void setWallpaper(String wallpaper);

    /**
     * Returns the floor id.
     * @return floor id
     */
    String getFloor();

    /**
     * Sets the floor id.
     * @param floor: new floor id
     */
    void setFloor(String floor);

    /**
     * Returns the landscape id.
     * @return landscape id
     */
    String getLandscape();

    /**
     * Sets the landscape id.
     * @param landscape: new landscape id
     */
    void setLandscape(String landscape);

    /**
     * Returns true if the trading is allowed.
     * @return trading allowed
     */
    boolean isTradingAllowed();

    /**
     * Sets if the trading is allowed.
     * @param tradingAllowed: new boolean
     */
    void setTradingAllowed(boolean tradingAllowed);

    /**
     * Returns the date creation.
     * @return date creation
     */
    String getDateCreation();

    /**
     * Sets the date creation.
     * @param dateCreation: new date creation
     */
    void setDateCreation(String dateCreation);

    /**
     * Returns true if pets are allowed.
     * @return pets allowed
     */
    boolean arePetsAllowed();

    /**
     * Sets if pets are allowed.
     * @param petsAllowed: new boolean
     */
    void setPetsAllowed(boolean petsAllowed);

    /**
     * TODO
     * Returns true if the entry is shown??.
     * @return
     */
    boolean isDisplayRoomEntryAd();

    /**
     * TODO
     * @param displayRoomEntryAd:
     */
    void setDisplayRoomEntryAd(boolean displayRoomEntryAd);
}
