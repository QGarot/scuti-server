package com.scuti.game.rooms;

import com.scuti.api.utils.IManager;
import com.scuti.game.rooms.mapping.RoomModel;
import com.scuti.storage.dao.rooms.RoomModelDao;
import com.scuti.util.logger.Logger;

import java.util.List;
import java.util.Objects;

public class RoomModelManager implements IManager {
    private static RoomModelManager instance;
    private RoomModelDao roomModelDao;
    private List<RoomModel> models;

    public RoomModelManager() {
        this.initialize();
    }

    @Override
    public void initialize() {
        this.roomModelDao = new RoomModelDao();
        this.models = this.getRoomModelDao().getAll();
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

    public List<RoomModel> getModels() {
        return models;
    }

    public RoomModel getModelByName(String name) {
        for (RoomModel model: this.getModels()) {
            if (Objects.equals(model.getName(), name)) {
                return model;
            }
        }

        return null;
    }
}
