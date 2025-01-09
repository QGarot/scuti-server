package com.scuti.storage.dao.navigator;

import com.scuti.game.components.navigator.entries.PublicRoomEntry;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NavigatorDao {
    public List<PublicRoomEntry> getPublicRooms() {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String sql = "SELECT * FROM navigator_publics";
        ArrayList<PublicRoomEntry> publicRooms= new ArrayList<>();

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                publicRooms.add(new PublicRoomEntry(resultSet.getInt("room_id"),
                        resultSet.getString("caption"),
                        resultSet.getString("caption"),
                        50,
                        resultSet.getInt("id"),
                        resultSet.getString("image"),
                        true));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }

        return publicRooms;
    }
}
