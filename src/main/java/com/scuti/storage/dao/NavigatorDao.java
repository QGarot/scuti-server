package com.scuti.storage.dao;

import com.scuti.game.navigator.PublicRoomEntry;
import com.scuti.game.navigator.RoomNavigator;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NavigatorDao {
    public static List<PublicRoomEntry> getPublicRooms() {
        String sql = "SELECT * FROM navigator_publics";
        ArrayList<PublicRoomEntry> publicRooms= new ArrayList<>();
        try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
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
        } catch (Exception e) {
            Logger.logError(e.getMessage());
        }

        return publicRooms;
    }

    public static List<RoomNavigator> getRoomsByOwnerUsername(String username) {
        String sql = "SELECT * FROM rooms WHERE owner = ?";
        ArrayList<RoomNavigator> rooms = new ArrayList<>();

        try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rooms.add(new RoomNavigator(
                        resultSet.getInt("id"),
                        resultSet.getString("caption"),
                        resultSet.getString("owner"),
                        resultSet.getString("state"),
                        resultSet.getInt("users_now"),
                        resultSet.getInt("users_max"),
                        resultSet.getString("description"),
                        true,
                        resultSet.getInt("score"),
                        resultSet.getInt("category"),
                        "06/01/25",
                        resultSet.getString("tags").split(";"),
                        resultSet.getInt("icon_bg"),
                        resultSet.getInt("icon_fg"),
                        resultSet.getInt("icon_items"),
                        1,
                        1,
                        resultSet.getBoolean("allow_pets"),
                        false
                ));
            }
        } catch (Exception e) {
            Logger.logError(e.getMessage());
        }

        return rooms;
    }
}
