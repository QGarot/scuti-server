package services.users;

import entities.users.IUserDetails;

import java.util.List;

public class UserService implements IUserService {
    @Override
    public List<IUser> getOnlineUsers() {

        return List.of();
    }

    @Override
    public IUser getUser(int userId) {
        IUser a = this.getOnlineUsers().getFirst();
        IUserDetails b = a.getDetails();
        return null;
    }

    @Override
    public IUser getUser(String username) {
        return null;
    }

    @Override
    public int authentication(String ticket) {
        return 0;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
