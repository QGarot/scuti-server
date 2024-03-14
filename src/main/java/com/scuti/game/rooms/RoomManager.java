package com.scuti.game.rooms;

import com.scuti.api.utils.IManager;
import com.scuti.game.users.User;
import com.scuti.storage.dao.rooms.RoomDao;
import com.scuti.util.logger.Logger;

import java.util.List;
import java.util.Objects;

public class RoomManager implements IManager {
    private static RoomManager instance;
    private RoomDao roomDao;
    private List<Room> roomsLoaded;

    public RoomManager() {
        this.initialize();
    }

    @Override
    public void initialize() {
        this.roomDao = new RoomDao();

        // Reset current visitors before loading rooms
        this.getRoomDao().resetVisitors();

        this.roomsLoaded = this.getRoomDao().getAll();
        Logger.logInfo("RoomManager loaded!");
    }

    @Override
    public void unload() {
        for (Room room: this.getRoomsLoaded()) {
            room.dispose();
        }
        this.getRoomsLoaded().clear();

        this.roomsLoaded = null;
        this.roomDao = null;
        instance = null;
        Logger.logInfo("RoomManager unloaded!");
    }

    /**
     * Load all rooms of a specific user
     * @param user:
     */
    public void loadRoomsForUser(User user) {
        for (Room room: this.getRoomsLoaded()) {
            if (Objects.equals(room.getDetails().getOwnerName(), user.getDetails().getUsername())) {
                user.getRooms().add(room);
            }
        }
    }

    public Room getRoomById(int id) {
        for (Room room: this.getRoomsLoaded()) {
            if (room.getDetails().getId() == id) {
                return room;
            }
        }

        return null;
    }

    public List<Room> getRoomsLoaded() {
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
