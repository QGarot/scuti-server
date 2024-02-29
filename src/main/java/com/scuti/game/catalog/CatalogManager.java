package com.scuti.game.catalog;

import com.scuti.api.utils.IManager;
import com.scuti.storage.dao.CatalogDao;
import com.scuti.util.logger.Logger;

import java.util.List;

public class CatalogManager implements IManager {
    private static CatalogManager instance;
    private List<CatalogPage> catalogPages;
    private List<CatalogItem> catalogItems;
    private CatalogDao catalogDao;

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
        this.catalogDao = new CatalogDao();
        this.catalogPages = this.getCatalogDao().getCatalogPages();
        this.catalogItems = this.getCatalogDao().getCatalogItems();
        Logger.logInfo("CatalogManager loaded!");
    }

    @Override
    public void unload() {
        this.getCatalogItems().clear();
        this.getCatalogPages().clear();
        this.catalogDao = null;
        this.catalogItems = null;
        this.catalogPages = null;
    }

    public CatalogDao getCatalogDao() {
        return this.catalogDao;
    }

    public List<CatalogPage> getCatalogPages() {
        return this.catalogPages;
    }

    public List<CatalogItem> getCatalogItems() {
        return this.catalogItems;
    }
}
