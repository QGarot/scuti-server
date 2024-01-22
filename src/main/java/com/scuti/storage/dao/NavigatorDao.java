package com.scuti.storage.dao;

import com.scuti.game.navigator.components.PublicRoomEntry;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NavigatorDao {
    public static List<PublicRoomEntry> getPublicRooms() {
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
                publicRooms.add(new PublicRoomEntry(
                        resultSet.getInt("id"),
                        resultSet.getString("caption"),
                        "",
                        resultSet.getString("image"),
                        0,
                        resultSet.getInt("ordernum"),
                        resultSet.getInt("roomid")
                ));
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
