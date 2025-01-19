package dao;

import entities.IEntity;

import java.util.List;

public interface Dao<T extends IEntity> {
    /**
     * Returns the content of the table containing T records.
     * @return list of corresponding instances
     */
    List<T> getAll();

    /**
     * Returns the T-object searched with given id.
     * @param id:
     * @return filled T-object
     */
    T get(int id);

    /**
     * Returns the T-object searched with given string parameter
     * (could be SSO Ticket, username, ...).
     * @param queryParam: string parameter which characterizes the searched record
     * @return filled T-object
     */
    T get(String queryParam);

    /**
     * Creates a new record corresponding to the given T object.
     * @param t: instance to insert in the database
     */
    void insert(T t);

    /**
     * Updates the record corresponding to the given T object.
     * @param t: instance to save in the database
     */
    void save(T t);
}
