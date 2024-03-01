package com.scuti.game.items;

import com.scuti.api.utils.IManager;
import com.scuti.storage.dao.ItemDao;
import com.scuti.util.logger.Logger;

import java.util.List;

public class ItemManager implements IManager {
    private static ItemManager instance;
    private ItemDao itemDao;
    private List<ItemBase> itemBases;

    public static ItemManager getInstance() {
        if (instance == null) {
            instance = new ItemManager();
        }

        return instance;
    }

    public ItemManager() {
        this.initialize();
    }

    @Override
    public void initialize() {
        this.itemDao = new ItemDao();
        this.itemBases = this.getItemDao().getItemBases();
        Logger.logInfo("Item manager loaded!");
    }

    @Override
    public void unload() {
        this.getItemBases().clear();
        this.itemDao = null;
        this.itemBases = null;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public List<ItemBase> getItemBases() {
        return itemBases;
    }

    /**
     * Get item base with its id
     * @param id: id of item base searched
     * @return item base:
     */
    public ItemBase getItemBaseById(int id) {
        for (ItemBase itemBase: this.getItemBases()) {
            if (itemBase.getId() == id) {
                return itemBase;
            }
        }
        return null;
    }
}
