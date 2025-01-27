package services;

import dao.Dao;
import entities.users.IUserDetails;
import game.users.IUser;
import game.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService implements IUserService {

    // DAO
    private final Dao<IUserDetails> userDetailsDao;

    // Map of users online
    private List<IUser> users;

    // Service status
    private boolean active;

    public UserService(Dao<IUserDetails> userDetailsDao) {
        this.userDetailsDao = userDetailsDao;
    }

    /**
     * Returns the user details dao.
     * @return user details dao
     */
    private Dao<IUserDetails> getUserDetailsDao() {
        return this.userDetailsDao;
    }

    @Override
    public List<IUser> getOnlineUsers() {
        return this.users;
    }

    @Override
    public IUser getUser(int userId) {
        for (IUser user: this.getOnlineUsers()) {
            if (user.getDetails().getId() == userId) {
                return user;
            }
        }

        return null;
    }

    @Override
    public IUser getUser(String username) {
        for (IUser user: this.getOnlineUsers()) {
            if (Objects.equals(user.getDetails().getUsername(), username)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public int authentication(String ticket) {
        IUserDetails details = this.getUserDetailsDao().get(ticket);

        // if 'details' is null, then no user is found with such a ticket
        if (details == null) {
            return -1;
        } else {
            // Create the user instance with dependencies injection: new User(.., .., ..)
            this.getOnlineUsers().add(new User(details));

            // Return the user id
            return details.getId();
        }
    }

    @Override
    public void start() {
        this.users = new ArrayList<>();
        this.active = true;
    }

    @Override
    public void stop() {
        this.getOnlineUsers().clear();
        this.users = null;
        this.active = false;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }
}
