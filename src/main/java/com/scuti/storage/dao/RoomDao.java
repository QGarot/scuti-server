package com.scuti.storage.dao;

import com.scuti.game.rooms.Room;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class RoomDao {
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
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }

        return rooms;
    }
}
