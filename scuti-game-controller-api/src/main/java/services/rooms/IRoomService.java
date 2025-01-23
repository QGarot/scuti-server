package services.rooms;

import entities.rooms.IRoomModel;
import services.IService;

import java.util.List;

public interface IRoomService extends IService {
    /**
     * Returns all loaded room models.
     * @return a list of room models
     */
    List<IRoomModel> getRoomModels();

    /**
     * Returns the corresponding room model.
     * @param name: name of the searched model
     * @return room model with the given name
     */
    IRoomModel getRoomModel(String name);

    /**
     * Returns the corresponding room.
     * @param id: id of the searched room
     * @return room with the given id
     */
    IRoom getRoom(int id);
}
