package com.scuti.storage.dao.rooms;

import com.scuti.api.dao.Dao;
import com.scuti.game.rooms.mapping.RoomModel;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomModelDao implements Dao<RoomModel> {
    @Override
    public List<RoomModel> getAll() {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        List<RoomModel> models = new ArrayList<>();
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
                models.add(model);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }

        return models;
    }

    @Override
    public void insert(RoomModel roomModel) {

    }

    @Override
    public void save(RoomModel roomModel) {

    }
}
