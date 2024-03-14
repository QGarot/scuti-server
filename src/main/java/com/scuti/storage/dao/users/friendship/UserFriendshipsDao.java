package com.scuti.storage.dao.users.friendship;

import com.scuti.api.dao.DaoComponent;
import com.scuti.game.users.components.friendship.Friendship;
import com.scuti.game.users.components.friendship.requests.Request;
import com.scuti.game.users.components.friendship.users.Buddy;
import com.scuti.game.users.components.friendship.users.UserSearched;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * INFO:
 * Table: user_friendships
 * user1_id corresponds to the user who made the request
 * user2_id corresponds to the user who received the request
 */
public class UserFriendshipsDao implements DaoComponent<Friendship> {
    @Override
    public Friendship get(int id) {
        List<Buddy> buddies = this.getFriends(id);
        System.out.println(buddies.size());
        List<Request> requests = this.getRequests(id);
        return new Friendship(buddies, requests);
    }

    @Override
    public void save(Friendship friendship) {

    }

    /**
     * Get friend requests received by the given user
     * @param receiverId:
     * @return a list of searched requests
     */
    private List<Request> getRequests(int receiverId) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String sql = "SELECT * FROM user_friendships WHERE user2_id = ? AND request = 1;";
        List<Request> requests = new ArrayList<>();

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, receiverId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                requests.add(new Request(resultSet.getInt("user1_id")));
            }

        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }

        return requests;
    }

    /**
     * Get friends of the given user
     * @param userId:
     * @return a list of friends
     */
    private List<Buddy> getFriends(int userId) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String sql = "SELECT users.id, users.username, users.gender, users.look, user_friendships.category_id, users.motto, user_friendships.following_allowed, users.online FROM user_friendships JOIN users ON users.id = user_friendships.user2_id WHERE user1_id = ? AND user_friendships.request = 0 " +
                "UNION " +
                "SELECT users.id, users.username, users.gender, users.look, user_friendships.category_id, users.motto, user_friendships.following_allowed, users.online FROM user_friendships JOIN users ON users.id = user_friendships.user1_id WHERE user2_id = ? AND user_friendships.request = 0;";

        List<Buddy> friends = new ArrayList<>();

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                friends.add(new Buddy(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        Objects.equals(resultSet.getString("gender"), "M") ? 1 : 0,
                        resultSet.getBoolean("online"),
                        resultSet.getBoolean("following_allowed"),
                        resultSet.getString("look"),
                        resultSet.getInt("category_id"),
                        resultSet.getString("motto"),
                        "",
                        ""));
            }

        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }

        return friends;
    }

    /**
     * Insert friend request in the database
     * @param receiverId: id of the user who receives the friend request
     * @param request:
     */
    public void insertFriendRequest(int receiverId, Request request) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String sql = "INSERT user_friendships (user1_id, user2_id, request, category_id, following_allowed)" +
                "VALUES (?, ?, ?, ?, ?);";
        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, request.getFromUserId());
            preparedStatement.setInt(2, receiverId);
            preparedStatement.setInt(3, 1);
            // default params
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, 1);
            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                request.setId(resultSet.getInt(1));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }
    }

    /**
     * Get a list of users found with such a username
     * @param username:
     * @return a max size ten list of users
     */
    public List<UserSearched> getUsersByUsername(String username) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        ArrayList<UserSearched> usersSearched = new ArrayList<>();
        String sql = "SELECT id, username, gender, look, motto, online FROM users WHERE username LIKE ? LIMIT 10";

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + username + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserSearched user = new UserSearched(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        Objects.equals(resultSet.getString("gender"), "M") ? 1 : 0,
                        resultSet.getBoolean("online"),
                        false,
                        resultSet.getString("look"),
                        resultSet.getString("motto"),
                        "");
                usersSearched.add(user);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }

        return usersSearched;
    }
}
