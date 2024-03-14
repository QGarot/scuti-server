package com.scuti.api.dao;

import java.util.List;

public interface Dao<T> {

    /**
     * Get the content of the table
     * @return list of corresponding instances
     */
    List<T> getAll();

    /**
     * Insert data corresponding to the given T object
     * @param t: instance to insert in the database
     */
    void insert(T t);

    /**
     * Update data corresponding to the given T object
     * @param t: instance to save in the database
     */
    void save(T t);

}
