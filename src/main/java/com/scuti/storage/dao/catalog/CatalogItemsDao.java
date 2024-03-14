package com.scuti.storage.dao.catalog;

import com.scuti.api.dao.Dao;
import com.scuti.game.catalog.CatalogItem;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CatalogItemsDao implements Dao<CatalogItem> {
    @Override
    public List<CatalogItem> getAll() {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        List<CatalogItem> catalogItems = new ArrayList<>();
        String sql = "SELECT id, page_id, item_id, catalog_name, cost_credits, cost_points, point_type, amount, vip, achievement, song_id FROM catalog_items;";

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CatalogItem catalogItem = new CatalogItem(resultSet.getInt("id"),
                        resultSet.getInt("item_id"),
                        resultSet.getString("catalog_name"),
                        resultSet.getInt("cost_credits"),
                        resultSet.getInt("cost_points"),
                        resultSet.getInt("point_type"),
                        resultSet.getInt("amount"),
                        resultSet.getInt("page_id"),
                        resultSet.getInt("vip"),
                        resultSet.getInt("achievement"),
                        resultSet.getInt("song_id"));
                catalogItems.add(catalogItem);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            Logger.logError(e.getMessage());
        }

        return catalogItems;
    }

    @Override
    public void insert(CatalogItem catalogItem) {

    }

    @Override
    public void save(CatalogItem catalogItem) {

    }
}
