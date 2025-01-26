package game;

import services.navigator.INavigatorService;
import services.rooms.IRoomService;
import services.users.IUserService;

public class GameController implements IGameController {
    @Override
    public IRoomService getRoomService() {
        return null;
    }

    @Override
    public INavigatorService getNavigatorService() {
        return null;
    }

    @Override
    public IUserService getUserService() {
        return null;
    }
}
