package com.scuti.game.catalog;

import com.scuti.api.utils.IManager;
import com.scuti.storage.dao.catalog.CatalogPagesDao;
import com.scuti.storage.dao.catalog.CatalogItemsDao;
import com.scuti.util.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class CatalogManager implements IManager {
    private static CatalogManager instance;
    private List<CatalogPage> catalogPages;
    private List<CatalogItem> catalogItems;
    private CatalogPagesDao catalogPagesDao;
    private CatalogItemsDao catalogItemsDao;

    public CatalogManager() {
        this.initialize();
    }

    public static CatalogManager getInstance() {
        if (instance == null) {
            instance = new CatalogManager();
        }

        return instance;
    }

    @Override
    public void initialize() {
        this.catalogPagesDao = new CatalogPagesDao();
        this.catalogItemsDao = new CatalogItemsDao();
        this.catalogPages = this.getCatalogPagesDao().getAll();
        this.catalogItems = this.getCatalogItemsDao().getAll();
        Logger.logInfo("CatalogManager loaded!");
    }

    @Override
    public void unload() {
        this.getCatalogItems().clear();
        this.getCatalogPages().clear();
        this.catalogPagesDao = null;
        this.catalogItemsDao = null;
        this.catalogItems = null;
        this.catalogPages = null;
    }

    public CatalogItemsDao getCatalogItemsDao() {
        return this.catalogItemsDao;
    }

    public CatalogPagesDao getCatalogPagesDao() {
        return this.catalogPagesDao;
    }

    public List<CatalogPage> getCatalogPages() {
        return this.catalogPages;
    }

    public List<CatalogItem> getCatalogItems() {
        return this.catalogItems;
    }

    public List<CatalogItem> getCatalogItemsOfPage(int pageId) {
        List<CatalogItem> items = new ArrayList<>();
        for (CatalogItem item: this.getCatalogItems()) {
            if (item.getPageId() == pageId) {
                items.add(item);
            }
        }
        return items;
    }

    /**
     * Get catalog page with its id
     * @param id:
     * @return catalog page searched:
     */
    public CatalogPage getCatalogPageById(int id) {
        for (CatalogPage page: this.getCatalogPages()) {
            if (page.getId() == id) {
                return page;
            }
        }
        return null;
    }
}
