package game;

import services.INavigatorService;
import services.IRoomService;
import services.IUserService;

public class GameController implements IGameController {

    // Game services
    private final IRoomService roomService;
    private final INavigatorService navigatorService;
    private final IUserService userService;

    public GameController(IUserService userService) {
        this.userService = userService;
        this.roomService = null;
        this.navigatorService = null;
    }



    @Override
    public IRoomService getRoomService() {
        return this.roomService;
    }

    @Override
    public INavigatorService getNavigatorService() {
        return this.navigatorService;
    }

    @Override
    public IUserService getUserService() {
        return this.userService;
    }
}
