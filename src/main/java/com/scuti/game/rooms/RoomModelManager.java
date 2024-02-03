package com.scuti.game.rooms;

import com.scuti.api.utils.IManager;
import com.scuti.game.rooms.mapping.RoomModel;
import com.scuti.storage.dao.RoomModelDao;
import com.scuti.util.logger.Logger;

import java.util.HashMap;

public class RoomModelManager implements IManager {
    private static RoomModelManager instance;
    private RoomModelDao roomModelDao;
    private HashMap<String, RoomModel> models;

    public RoomModelManager() {
        this.initialize();
    }

    @Override
    public void initialize() {
        this.roomModelDao = new RoomModelDao();
        this.models = this.getRoomModelDao().getModels();
        Logger.logInfo("RoomModelManager loaded!");
    }

    @Override
    public void unload() {
        this.getModels().clear();

        this.roomModelDao = null;
        this.models = null;
        instance = null;
        Logger.logInfo("RoomModelManager unloaded!");
    }

    public RoomModelDao getRoomModelDao() {
        return roomModelDao;
    }

    public static RoomModelManager getInstance() {
        if (instance == null) {
            instance = new RoomModelManager();
        }

        return instance;
    }

    public HashMap<String, RoomModel> getModels() {
        return models;
    }
}
