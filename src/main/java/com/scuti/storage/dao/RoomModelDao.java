package com.scuti.storage.dao;

import com.scuti.game.rooms.mapping.RoomModel;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class RoomModelDao {
    public HashMap<String, RoomModel> getModels() {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        HashMap<String, RoomModel> models = new HashMap<>();
        String sql = "SELECT * FROM room_models;";

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RoomModel model = new RoomModel(
                        resultSet.getString("id"),
                        resultSet.getInt("door_x"),
                        resultSet.getInt("door_y"),
                        resultSet.getInt("door_z"),
                        resultSet.getInt("door_dir"),
                        resultSet.getString("heightmap"),
                        resultSet.getBoolean("club_only")
                );
                models.put(model.getName(), model);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }

        return models;
    }
}
