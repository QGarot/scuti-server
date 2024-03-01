package com.scuti.storage.dao;

import com.scuti.game.catalog.CatalogItem;
import com.scuti.game.catalog.CatalogPage;
import com.scuti.storage.Database;
import com.scuti.util.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CatalogDao {

    public List<CatalogItem> getCatalogItems() {
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
    public List<CatalogPage> getCatalogPages() {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        List<CatalogPage> catalogPages = new ArrayList<>();
        String sql = "SELECT * FROM catalog_pages ORDER BY order_num;";

        try {
            connection = Database.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CatalogPage page = new CatalogPage(resultSet.getInt("id"),
                        resultSet.getInt("parent_id"),
                        resultSet.getString("caption"),
                        resultSet.getBoolean("visible"),
                        true,
                        resultSet.getInt("min_rank"),
                        resultSet.getBoolean("club_only"),
                        resultSet.getInt("icon_color"),
                        resultSet.getInt("icon_image"),
                        resultSet.getString("page_layout"),
                        resultSet.getString("page_headline"),
                        resultSet.getString("page_teaser"),
                        resultSet.getString("page_special"),
                        resultSet.getString("page_text1"),
                        resultSet.getString("page_text2"),
                        resultSet.getString("page_text_details"),
                        resultSet.getString("page_text_teaser"),
                        resultSet.getString("page_link_description"),
                        resultSet.getString("page_link_pagename"));
                catalogPages.add(page);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            Logger.logError(e.getMessage());
        }

        return catalogPages;
    }
}
