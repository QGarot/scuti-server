package com.scuti.storage.dao;

import com.scuti.game.users.User;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public void saveDetails(User user) {
        Connection connection;
        PreparedStatement preparedStatement;

        String sql = "UPDATE users " +
                "SET username = ?, look = ?, motto = ?, rank = ?, credits = ?, pixels = ?, shells = ?, online = ? " +
                "WHERE id = ?;";

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getDetails().getUsername());
            preparedStatement.setString(2, user.getDetails().getFigure());
            preparedStatement.setString(3, user.getDetails().getMotto());
            preparedStatement.setInt(4, user.getDetails().getRank());
            preparedStatement.setInt(5, user.getDetails().getCredits());
            preparedStatement.setInt(6, user.getDetails().getPixels());
            preparedStatement.setInt(7, user.getDetails().getShells());
            preparedStatement.setBoolean(8, user.getDetails().isOnline());
            preparedStatement.setInt(9, user.getDetails().getId());
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }
    }

    public boolean loginSSO(User user, String ticket) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String sql = "SELECT id, username, mail, look, motto, gender, auth_ticket, rank, credits, pixels, shells, online " +
                "FROM users " +
                "WHERE auth_ticket = ?;";
        boolean success = false;

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ticket);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                success = true;
                user.getDetails().fill(resultSet.getInt("id"),
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
        return success;
    }
}
