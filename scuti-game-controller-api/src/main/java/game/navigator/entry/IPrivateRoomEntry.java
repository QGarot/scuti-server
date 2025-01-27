package game.navigator.entry;

import game.navigator.thumbnail.IThumbnail;

public interface IPrivateRoomEntry extends IRoomEntry {
    /**
     * Returns true if there is an event in this room
     * @return event in this room
     */
    boolean isEvent();

    /**
     * Returns the room state.
     * @return room state
     */
    int getState();

    /**
     * Returns true if players can trade in this room.
     * @return trading allowed
     */
    boolean canTrade();

    /**
     * Returns the score of this room.
     * @return score
     */
    int getScore();

    /**
     * Returns the category id.
     * @return category id
     */
    int getCategory();

    /**
     * Returns the date creation.
     * @return date creation
     */
    String getDateCreation();

    /**
     * Returns the tags.
     * @return tags
     */
    String getTags();

    /**
     * Returns the thumbnail.
     * @return thumbnail
     */
    IThumbnail getThumbnail();
}
