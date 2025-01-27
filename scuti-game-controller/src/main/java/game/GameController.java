package game;

import services.INavigatorService;
import services.IRoomService;
import services.IUserService;

public class GameController implements IGameController {

    // Game services
    private IRoomService roomService;
    private INavigatorService navigatorService;
    private IUserService userService;

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
