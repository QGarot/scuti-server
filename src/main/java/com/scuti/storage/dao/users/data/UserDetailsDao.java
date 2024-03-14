package com.scuti.storage.dao.users.data;

import com.scuti.api.dao.DaoComponent;
import com.scuti.game.users.components.data.UserDetails;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsDao implements DaoComponent<UserDetails> {
    @Override
    public UserDetails get(int id) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        UserDetails userDetails = null;

        String sql = "SELECT id, username, mail, look, motto, gender, auth_ticket, rank, credits, pixels, shells, online " +
                "FROM users " +
                "WHERE id = ?;";

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userDetails = new UserDetails(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("mail"),
                        resultSet.getString("look"),
                        resultSet.getString("motto"),
                        resultSet.getString("gender"),
                        resultSet.getString("auth_ticket"),
                        resultSet.getInt("rank"),
                        resultSet.getInt("credits"),
                        resultSet.getInt("pixels"),
                        resultSet.getInt("shells"),
                        resultSet.getBoolean("online"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }

        return userDetails;
    }

    @Override
    public void save(UserDetails details) {
        Connection connection;
        PreparedStatement preparedStatement;

        String sql = "UPDATE users " +
                "SET username = ?, look = ?, motto = ?, rank = ?, credits = ?, pixels = ?, shells = ?, online = ? " +
                "WHERE id = ?;";

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, details.getUsername());
            preparedStatement.setString(2, details.getFigure());
            preparedStatement.setString(3, details.getMotto());
            preparedStatement.setInt(4, details.getRank());
            preparedStatement.setInt(5, details.getCredits());
            preparedStatement.setInt(6, details.getPixels());
            preparedStatement.setInt(7, details.getShells());
            preparedStatement.setBoolean(8, details.isOnline());
            preparedStatement.setInt(9, details.getId());
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }
    }
}
