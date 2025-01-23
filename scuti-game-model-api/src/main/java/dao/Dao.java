package dao;

import database.IDatabase;
import entities.IEntity;

public abstract class Dao<T extends IEntity> implements IDao<T> {

    private final IDatabase db;

    public Dao(IDatabase db) {
        this.db = db;
    }

    /**
     * Returns the database.
     * @return database
     */
    public IDatabase getDatabase() {
        return this.db;
    }
}
