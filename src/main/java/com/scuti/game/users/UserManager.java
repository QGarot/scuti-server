package com.scuti.game.users;

import com.scuti.api.utils.IManager;
import com.scuti.game.users.components.data.UserDetails;
import com.scuti.game.users.components.friendship.Friendship;
import com.scuti.storage.dao.users.UserDao;
import com.scuti.storage.dao.users.data.UserDetailsDao;
import com.scuti.storage.dao.users.friendship.UserFriendshipsDao;
import com.scuti.util.logger.Logger;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserManager implements IManager {
    private static UserManager instance;
    private List<User> users;
    private UserDao userDao;
    private UserDetailsDao userDetailsDao;
    private UserFriendshipsDao userFriendshipsDao;

    private UserManager() {
        this.initialize();
    }

    @Override
    public void initialize() {
        this.userDao = new UserDao();
        this.userDetailsDao = new UserDetailsDao();
        this.userFriendshipsDao = new UserFriendshipsDao();

        // Reset users status
        this.getUserDao().resetUsersStatus();

        this.users = new ArrayList<>();
        Logger.logInfo("UserManager loaded!");
    }

    @Override
    public void unload() {
        Logger.logInfo("Disconnection of all connected users...");
        for (User user: this.getUsers()) {
            user.disconnect(1);
        }
        //Logger.logInfo("UserManager unloaded!");
    }

    public UserFriendshipsDao getUserFriendshipsDao() {
        return userFriendshipsDao;
    }

    public UserDetailsDao getUserDetailsDao() {
        return userDetailsDao;
    }

    public UserDao getUserDao() {
        return this.userDao;
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }

        return instance;
    }

    public void displayUsers() {
        // debug
        System.out.println("----------Users----------");
        for (User user: this.users) {
            System.out.println(user.getNetwork().getChannel().localAddress().toString());
        }
        System.out.println("----------end users----------");
    }

    public User getUserByChannel(Channel channel) {
        for (User user: this.users) {
            if (user.getNetwork().getChannel() == channel) {
                return user;
            }
        }
        return null;
    }

    public User getUserById(int id) {
        for (User user: this.users) {
            if (user.getDetails().getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User getUserByUsername(String username) {
        for (User user: this.users) {
            if (Objects.equals(user.getDetails().getUsername(), username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUsers() {
        return this.users;
    }

    /**
     * Check SSO login and fill user params
     * @param user: attempted user
     * @param SSOTicket: connection ticket
     */
    public void loginSSO(User user, String SSOTicket) {
        if (this.getUserDao().validSSOTicket(user, SSOTicket)) {
            // Search all user params, fill components
            UserDetails details = this.getUserDetailsDao().get(user.getId());
            Friendship friendship = this.getUserFriendshipsDao().get(user.getId());

            // Log user
            user.login(details, friendship);

            this.getUserDetailsDao().save(user.getDetails());
            Logger.logInfo(user.getDetails().getUsername().concat(" is now connected!"));
        } else {
            user.disconnect(1);
        }
    }
}
