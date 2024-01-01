package com.scuti.storage.dao;

import com.scuti.game.users.User;
import com.scuti.game.users.messenger.Buddy;
import com.scuti.game.users.messenger.UserSearched;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FriendDao {
    public static List<UserSearched> getUsersByUsername(String username) {
        ArrayList<UserSearched> usersSearched = new ArrayList<>();
        String sql = "SELECT id, username, gender, look, motto, online " +
                "FROM users " +
                "WHERE username LIKE ? LIMIT 10";
        try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + username + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                username = resultSet.getString("username");
                int gender = resultSet.getString("gender") == "M" ? 1 : 0;
                boolean online = resultSet.getBoolean("online");
                String figure = resultSet.getString("look");
                String motto = resultSet.getString("motto");

                UserSearched user = new UserSearched(id, username, gender, online, false, figure, motto, "");
                usersSearched.add(user);
            }
        } catch (Exception e) {
            Logger.logError(e.getMessage());
        }

        return usersSearched;
    }
    public static void fillFriendsOf(User user) {
        String sql = "SELECT users.id, users.username, users.gender, users.look, buddies.category_id, users.motto, buddies.following_allowed, users.online " +
                "FROM buddies " +
                "JOIN users ON users.id = buddies.user2_id " +
                "WHERE user1_id = ?;";
        // clear buddies list
        user.getMessenger().getBuddies().clear();
        // fill buddies list
        try(PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getDetails().getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                int gender = resultSet.getString("gender") == "M" ? 1 : 0;
                boolean online = resultSet.getBoolean("online");
                boolean followingAllowed = resultSet.getBoolean("following_allowed");
                String figure = resultSet.getString("look");
                int categoryId = resultSet.getInt("category_id");
                String motto = resultSet.getString("motto");
                String lastLogin = ""; // TODO
                String facebookId = ""; // TODO

                Buddy buddy = new Buddy(id, username, gender, online, followingAllowed, figure, categoryId, motto, lastLogin, facebookId);
                user.getMessenger().addBuddy(buddy);
            }
        } catch (Exception e) {
            Logger.logError(e.getMessage());
        }
    }
}
