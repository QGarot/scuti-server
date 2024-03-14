package com.scuti.storage.dao.rooms;

import com.scuti.api.dao.Dao;
import com.scuti.game.rooms.Room;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao implements Dao<Room> {
    @Override
    public List<Room> getAll() {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Room room = new Room();
                room.getDetails().fill(
                        resultSet.getInt("id"),
                        resultSet.getString("roomtype"),
                        resultSet.getString("caption"),
                        resultSet.getString("owner"),
                        resultSet.getString("description"),
                        resultSet.getInt("category"),
                        resultSet.getString("state"),
                        resultSet.getInt("users_now"),
                        resultSet.getInt("users_max"),
                        resultSet.getString("model_name"),
                        resultSet.getInt("score"),
                        resultSet.getString("tags"),
                        resultSet.getString("password"),
                        resultSet.getString("wallpaper"),
                        resultSet.getString("floor"),
                        resultSet.getString("landscape"),
                        resultSet.getBoolean("allow_trading"),
                        resultSet.getBoolean("allow_pets"),
                        resultSet.getBoolean("visible"),
                        "05/09/2010"
                );

                room.getThumbnail().fill(
                        resultSet.getInt("icon_bg"),
                        resultSet.getInt("icon_fg"),
                        resultSet.getInt("icon_item_count"),
                        resultSet.getInt("icon_item_position"),
                        resultSet.getInt("icon_item_image_id")
                );
                rooms.add(room);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }

        return rooms;
    }

    @Override
    public void insert(Room room) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String sql = "INSERT rooms (caption, owner, model_name) " +
                "VALUES (?, ?, ?);";

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, room.getDetails().getCaption());
            preparedStatement.setString(2, room.getDetails().getOwnerName());
            preparedStatement.setString(3, room.getDetails().getModelName());

            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()) {
                room.getDetails().setId(resultSet.getInt(1));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }
    }

    @Override
    public void save(Room room) {
        Connection connection;
        PreparedStatement preparedStatement;

        String sql = "UPDATE rooms SET roomtype = ?, caption = ?, description = ?, category = ?, state = ?, users_now = ?, users_max = ?, model_name = ?, score = ?, tags = ?, password = ?, wallpaper = ?, floor = ?, landscape = ?, allow_trading = ?, allow_pets = ? WHERE id = ?";

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, room.getDetails().getType());
            preparedStatement.setString(2, room.getDetails().getCaption());
            preparedStatement.setString(3, room.getDetails().getDescription());
            preparedStatement.setInt(4, room.getDetails().getCategory());
            preparedStatement.setString(5, room.getDetails().getState() == 0 ? "open" : (room.getDetails().getState() == 1 ? "locked" : "password"));
            preparedStatement.setInt(6, room.getDetails().getUsersNow());
            preparedStatement.setInt(7, room.getDetails().getUsersMax());
            preparedStatement.setString(8, room.getDetails().getModelName());
            preparedStatement.setInt(9, room.getDetails().getScore());
            preparedStatement.setString(10, room.getDetails().getTags());
            preparedStatement.setString(11, room.getDetails().getPassword());
            preparedStatement.setString(12, room.getDetails().getWallpaper());
            preparedStatement.setString(13, room.getDetails().getFloor());
            preparedStatement.setString(14, room.getDetails().getLandscape());
            preparedStatement.setBoolean(15, room.getDetails().isTradingAllowed());
            preparedStatement.setBoolean(16, room.getDetails().arePetsAllowed());
            preparedStatement.setInt(17, room.getDetails().getId());
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }
    }

    /**
     * Set the current visitors number to 0 for all rooms registered in the database
     */
    public void resetVisitors() {
        Connection connection;
        PreparedStatement preparedStatement;

        String sql = "UPDATE rooms SET users_now = 0 WHERE users_now > 0;";

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }
    }
}
