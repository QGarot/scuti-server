package game.rooms;

import disposable.Disposable;
import entities.rooms.IRoomDetails;
import entities.rooms.IRoomModel;

public interface IRoom extends Disposable {
    /**
     * Returns the room details.
     * @return room details
     */
    IRoomDetails getDetails();

    /**
     * Returns the room model.
     * @return room model
     */
    IRoomModel getModel();
}
