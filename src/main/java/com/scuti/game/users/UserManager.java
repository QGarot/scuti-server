package com.scuti.game.users;

import com.scuti.api.utils.IManager;
import com.scuti.storage.dao.UserDao;
import com.scuti.util.logger.Logger;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserManager implements IManager {
    private static UserManager instance;
    private List<User> users;
    private UserDao userDao;

    private UserManager() {
        this.initialize();
    }


    @Override
    public void initialize() {
        this.userDao = new UserDao();
        this.users = new ArrayList<>();
    }

    @Override
    public void unload() {
        Logger.logInfo("Disconnection of all connected users...");
        for (User user: this.getUsers()) {
            user.disconnect(1);
        }
        //Logger.logInfo("UserManager unloaded!");
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
}
