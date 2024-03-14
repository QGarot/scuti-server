package com.scuti.storage.dao.furnitures;

import com.scuti.api.dao.Dao;
import com.scuti.game.items.ItemBase;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao implements Dao<ItemBase> {
    @Override
    public List<ItemBase> getAll() {
        List<ItemBase> itemBases = new ArrayList<>();
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String sql = "SELECT * FROM furnitures";

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                itemBases.add(new ItemBase(resultSet.getInt("id"),
                        resultSet.getString("public_name"),
                        resultSet.getString("item_name"),
                        resultSet.getString("type"),
                        resultSet.getInt("width"),
                        resultSet.getInt("length"),
                        resultSet.getInt("stack_height"),
                        resultSet.getBoolean("can_stack"),
                        resultSet.getBoolean("can_sit"),
                        resultSet.getBoolean("is_walkable"),
                        resultSet.getInt("sprite_id"),
                        resultSet.getBoolean("allow_recycle"),
                        resultSet.getBoolean("allow_trade"),
                        resultSet.getBoolean("allow_marketplace_sell"),
                        resultSet.getBoolean("allow_gift"),
                        resultSet.getBoolean("allow_inventory_stack"),
                        resultSet.getString("interaction_type"),
                        resultSet.getInt("interaction_modes_count"),
                        0,
                        resultSet.getBoolean("is_arrow"),
                        resultSet.getBoolean("height_adjustable"),
                        resultSet.getInt("effectM"),
                        resultSet.getInt("effectF")));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Logger.logError(e.getMessage());
        }

        return itemBases;
    }

    @Override
    public void insert(ItemBase itemBase) {

    }

    @Override
    public void save(ItemBase itemBase) {

    }
}
