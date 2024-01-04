package com.scuti.storage.dao;

import com.scuti.game.users.User;
import com.scuti.game.users.components.messenger.users.Buddy;
import com.scuti.game.users.components.messenger.requests.Request;
import com.scuti.game.users.components.messenger.users.UserSearched;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FriendDao {
    public static void insertRequestBuddyAndFillId(Request request) {
        String sql = "INSERT buddies (user1_id, user2_id, request, category_id, following_allowed)" +
                "VALUES (?, ?, ?, ?, ?) ;";
        try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, request.getFromUserId());
            preparedStatement.setInt(2, request.getToUserId());
            preparedStatement.setInt(3, 1);
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, 1);

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                request.setId(resultSet.getInt(1));
            }
        } catch (Exception e) {
            Logger.logError(e.getMessage());
        }
    }

    public static List<UserSearched> searchUserByUsername(String username) {
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
