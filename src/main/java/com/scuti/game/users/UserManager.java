package com.scuti.game.users;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserManager {
    private static UserManager instance;
    private List<User> users;

    private UserManager() {
        this.users = new ArrayList<User>();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }

        return instance;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
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
