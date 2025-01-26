package game;

import services.navigator.INavigatorService;
import services.rooms.IRoomService;
import services.users.IUserService;

public interface IGameController {
    /**
     * Returns the room service.
     * @return room service
     */
    IRoomService getRoomService();

    /**
     * Returns the navigator service.
     * @return navigator service
     */
    INavigatorService getNavigatorService();

    /**
     * Returns the user service.
     * @return room service
     */
    IUserService getUserService();
}
