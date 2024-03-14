package com.scuti.storage.dao.users;

import com.scuti.api.dao.Dao;
import com.scuti.game.users.User;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements Dao<User> {

    /**
     * Check if the given ticket exists in the users table.
     * If it is then fill the user id.
     * @param user:
     * @param SSOTicket:
     * @return corresponding boolean
     */
    public boolean validSSOTicket(User user, String SSOTicket) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String sql = "SELECT id FROM users WHERE auth_ticket = ?;";
        boolean success = false;

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, SSOTicket);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                success = true;
                user.setId(resultSet.getInt("id"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }
        return success;
    }

    /**
     * Set the online status to 0 for all users registered in the database
     */
    public void resetUsersStatus() {
        Connection connection;
        PreparedStatement preparedStatement;

        String sql = "UPDATE users SET online = 0 WHERE online = 1;";

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            Logger.logError(e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void save(User user) {

    }
}
