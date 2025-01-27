package game.navigator.entry;

public interface IPublicRoomEntry extends IRoomEntry {
    /**
     * Returns the id of this public room.
     * @return public room id
     */
    int getId();

    /**
     * Returns the image players can see in the navigator.
     * @return image
     */
    String getImage();

    /**
     * Returns true if this public room is recommended by the staff.
     * @return recommended
     */
    boolean isRecommended();
}
