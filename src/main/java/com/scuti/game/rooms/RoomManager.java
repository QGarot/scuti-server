package com.scuti.game.rooms;

import com.scuti.api.utils.IManager;
import com.scuti.game.users.User;
import com.scuti.storage.dao.RoomDao;
import com.scuti.util.logger.Logger;

import java.util.HashMap;
import java.util.Objects;

public class RoomManager implements IManager {
    private static RoomManager instance;
    private RoomDao roomDao;
    private HashMap<Integer, Room> roomsLoaded;

    public RoomManager() {
        this.initialize();
    }

    @Override
    public void initialize() {
        this.roomDao = new RoomDao();
        this.roomsLoaded = this.getRoomDao().getRooms();
        Logger.logInfo("RoomManager loaded!");
    }

    public void loadRoomsForUser(User user) {
        for (Room room: this.getRoomsLoaded().values()) {
            if (Objects.equals(room.getDetails().getOwnerName(), user.getDetails().getUsername())) {
                user.getRooms().add(room);
            }
        }
    }

    public HashMap<Integer, Room> getRoomsLoaded() {
        return roomsLoaded;
    }

    public RoomDao getRoomDao() {
        return roomDao;
    }

    public static RoomManager getInstance() {
        if (instance == null) {
            instance = new RoomManager();
        }

        return instance;
    }
}
