package com.scuti.providers;

import dao.Dao;
import dao.UserDetailsDao;
import database.IDatabase;
import entities.users.IUserDetails;
import services.IService;
import services.IUserService;
import services.UserService;

public class UserServiceProvider extends ServiceProvider<IUserService> {

    // DAO
    private final Dao<IUserDetails> userDetailsDao;

    public UserServiceProvider(IDatabase db) {
        super(db);
        this.userDetailsDao = new UserDetailsDao(this.db);
    }

    @Override
    public IUserService getService() {
        return new UserService(this.userDetailsDao);
    }
}
