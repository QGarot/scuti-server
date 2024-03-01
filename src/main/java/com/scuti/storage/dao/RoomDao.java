package com.scuti.storage.dao;

import com.scuti.game.rooms.Room;
import com.scuti.game.rooms.components.data.RoomDetails;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.*;
import java.util.HashMap;

public class RoomDao {
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

    public HashMap<Integer, Room> getRooms() {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        HashMap<Integer, Room> rooms = new HashMap<>();
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
                rooms.put(room.getDetails().getId(), room);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }

        return rooms;
    }

    public void saveRoomDetails(RoomDetails roomDetails) {
        Connection connection;
        PreparedStatement preparedStatement;

        String sql = "UPDATE rooms SET roomtype = ?, caption = ?, description = ?, category = ?, state = ?, users_now = ?, users_max = ?, model_name = ?, score = ?, tags = ?, password = ?, wallpaper = ?, floor = ?, landscape = ?, allow_trading = ?, allow_pets = ? WHERE id = ?";

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, roomDetails.getType());
            preparedStatement.setString(2, roomDetails.getCaption());
            preparedStatement.setString(3, roomDetails.getDescription());
            preparedStatement.setInt(4, roomDetails.getCategory());
            preparedStatement.setString(5, roomDetails.getState() == 0 ? "open" : (roomDetails.getState() == 1 ? "locked" : "password"));
            preparedStatement.setInt(6, roomDetails.getUsersNow());
            preparedStatement.setInt(7, roomDetails.getUsersMax());
            preparedStatement.setString(8, roomDetails.getModelName());
            preparedStatement.setInt(9, roomDetails.getScore());
            preparedStatement.setString(10, roomDetails.getTags());
            preparedStatement.setString(11, roomDetails.getPassword());
            preparedStatement.setString(12, roomDetails.getWallpaper());
            preparedStatement.setString(13, roomDetails.getFloor());
            preparedStatement.setString(14, roomDetails.getLandscape());
            preparedStatement.setBoolean(15, roomDetails.isTradingAllowed());
            preparedStatement.setBoolean(16, roomDetails.arePetsAllowed());
            preparedStatement.setInt(17, roomDetails.getId());
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }
    }

    public void insertRoomAndFillId(Room room) {
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
}
