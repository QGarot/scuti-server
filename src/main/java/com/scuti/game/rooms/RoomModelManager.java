package com.scuti.game.rooms;

import com.scuti.api.utils.IManager;
import com.scuti.game.rooms.mapping.RoomModel;
import com.scuti.storage.dao.RoomModelDao;

import java.util.ArrayList;
import java.util.List;

public class RoomModelManager implements IManager {
    private static RoomModelManager instance;
    private final List<RoomModel> models;
    private RoomModelDao roomModelDao;

    public RoomModelManager() {
        this.models = new ArrayList<>();
        this.roomModelDao = new RoomModelDao();
    }

    @Override
    public void initialize() {

    }

    public RoomModelManager getInstance() {
        if (instance == null) {
            instance = new RoomModelManager();
        }

        return instance;
    }
}
