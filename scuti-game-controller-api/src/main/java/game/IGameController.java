package game;

import services.INavigatorService;
import services.IRoomService;
import services.IUserService;

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
