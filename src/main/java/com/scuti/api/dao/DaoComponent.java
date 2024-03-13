package com.scuti.api.dao;

public interface DaoComponent<T> {

    /**
     * Get object searched with given id
     * @param id:
     * @return filled component
     */
    T get(int id);

    /**
     * Update the database entry
     * @param t: component to save data
     */
    void save(T t);
}
