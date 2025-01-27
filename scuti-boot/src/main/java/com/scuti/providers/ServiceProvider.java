package com.scuti.providers;

import database.IDatabase;
import services.IService;

/**
 * The role of a service provider is to build the corresponding service with all instances it needs.
 * For example, the service provider will build all DAO objects (thanks to the database instance) and will inject them
 * in the service constructor.
 */
public abstract class ServiceProvider {
    protected IDatabase db;

    public ServiceProvider(IDatabase db) {
        this.db = db;
    }

    /**
     * Returns the corresponding service.
     * @return the service if it is ready to be used, null else
     */
    public abstract IService getService();
}
