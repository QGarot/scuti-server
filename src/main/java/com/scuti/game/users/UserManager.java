package com.scuti.game.users;

import com.scuti.api.utils.IManager;
import com.scuti.game.users.components.data.UserDetails;
import com.scuti.game.users.components.friendship.Friendship;
import com.scuti.messages.outgoing.handshake.AuthenticationOKMessageComposer;
import com.scuti.messages.outgoing.users.MotdNotificationMessageComposer;
import com.scuti.server.netty.connections.NettyConnection;
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
            //user.disconnect(1);
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
     * SSO Login
     * @param connection: attempted client
     * @param SSOTicket: connection ticket
     */
    public void loginSSO(NettyConnection connection, String SSOTicket) {
        int userId = this.getUserDao().getUserIdBySSOTicket(SSOTicket);
        if (userId > 0) {
            // Search all user params, fill components
            UserDetails details = this.getUserDetailsDao().get(userId);
            Friendship friendship = this.getUserFriendshipsDao().get(userId);

            // Create user and associate it to the netty connection
            User user = new User(details, friendship);
            connection.setUser(user);

            // Made the connection to the client
            connection.send(new AuthenticationOKMessageComposer());
            connection.send(new MotdNotificationMessageComposer());
            connection.getUser().getDetails().setOnline(true);

            // Save online status and log the connection
            this.getUserDetailsDao().save(user.getDetails());
            Logger.logInfo(user.getDetails().getUsername().concat(" is now connected!"));
        } else {
            connection.disconnect();
        }
    }
}
