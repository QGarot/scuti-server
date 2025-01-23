package services.navigator;

import services.IService;
import services.navigator.entry.IPrivateRoomEntry;
import services.navigator.entry.IPublicRoomEntry;

import java.util.List;

public interface INavigatorService extends IService {
    /**
     * Returns all public rooms.
     * @return a list of public rooms
     */
    List<IPublicRoomEntry> getPublicRooms();

    /**
     * Returns all popular rooms, ie the rooms with the current most important players in.
     * @return a list of popular rooms
     */
    List<IPrivateRoomEntry> getPopularRooms();

    /**
     * Returns all owned rooms by the corresponding user.
     * @param userId: id of user
     * @return a list of owned rooms
     */
    List<IPrivateRoomEntry> getOwnedRooms(int userId);

    /**
     * Returns all rooms corresponding to the query.
     * @param query: pattern for room caption
     * @return a list of searched rooms with this pattern
     */
    List<IPrivateRoomEntry> getSearchedRooms(String query);

    /**
     * Creates a room.
     * @param ownerName: username of the player who wants to create the room
     * @param caption: name of the wanted room
     * @param model: layout of the wanted room
     */
    void createRoom(String ownerName, String caption, String model);
}
