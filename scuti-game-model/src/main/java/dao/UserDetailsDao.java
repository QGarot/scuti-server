package dao;

import database.IDatabase;
import entities.users.IUserDetails;
import entities.users.UserDetails;
import logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDetailsDao extends Dao<IUserDetails> {

    public UserDetailsDao(IDatabase db) {
        super(db);
    }

    @Override
    public List<IUserDetails> getAll() {
        return null;
    }

    @Override
    public UserDetails get(int id) {
        return null;
    }

    @Override
    public UserDetails get(String queryParam) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String sql = "SELECT id FROM users WHERE auth_ticket = ?;";

        try {
            connection = this.getDatabase().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, queryParam);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new UserDetails(resultSet.getInt("id"),
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

        return null;
    }

    @Override
    public void insert(IUserDetails details) {

    }

    @Override
    public void save(IUserDetails details) {
        Connection connection;
        PreparedStatement preparedStatement;

        String sql = "UPDATE users " +
                "SET username = ?, look = ?, motto = ?, rank = ?, credits = ?, pixels = ?, shells = ?, online = ? " +
                "WHERE id = ?;";

        try {
            connection = this.getDatabase().getConnection();
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
