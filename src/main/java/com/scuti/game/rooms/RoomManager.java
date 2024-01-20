package com.scuti.game.rooms;

import com.scuti.api.utils.IManager;

public class RoomManager implements IManager {
    private static RoomManager instance;
    @Override
    public void initialize() {

    }

    public RoomManager getInstance() {
        if (instance == null) {
            instance = new RoomManager();
        }

        return instance;
    }
}
