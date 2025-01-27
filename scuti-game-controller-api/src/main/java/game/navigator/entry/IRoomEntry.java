package game.navigator.entry;

public interface IRoomEntry {
    /**
     * Returns the corresponding room id.
     * @return room id
     */
    int getRoomId();

    /**
     * Returns the room name.
     * @return room name
     */
    String getCaption();

    /**
     * Returns the room description.
     * @return room description
     */
    String getDescription();

    /**
     * Returns the room users capacity.
     * @return room users capacity
     */
    int getUsersMax();
}
