package com.scuti.storage.dao;

import com.scuti.game.users.User;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public static boolean loginSSO(User user, String ticket) {
        String sql = "SELECT id, username, mail, look, motto, gender, auth_ticket, rank, credits, pixels, shells " +
                "FROM users " +
                "WHERE auth_ticket = ?;";
        boolean success = false;
        try(PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, ticket);
            ResultSet resultSet = preparedStatement.executeQuery();
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
                        resultSet.getInt("shells"));
           }
        } catch (Exception e) {
            Logger.logError(e.getMessage());
        }
        return success;
    }
}
