package com.scuti.game.components.navigator;

import com.scuti.api.game.IHotelComponent;
import com.scuti.game.components.navigator.entries.PublicRoomEntry;
import com.scuti.storage.dao.navigator.NavigatorDao;

import java.util.List;

public class NavigatorComponent implements IHotelComponent {
    private static NavigatorComponent instance;
    private NavigatorDao navigatorDao;
    private List<PublicRoomEntry> publicRoomEntries;

    public NavigatorComponent() {
        this.load();
    }

    public static NavigatorComponent getInstance() {
        if (instance == null) {
            instance = new NavigatorComponent();
        }
        return instance;
    }

    public NavigatorDao getNavigatorDao() {
        return navigatorDao;
    }

    public List<PublicRoomEntry> getPublicRoomEntries() {
        return publicRoomEntries;
    }

    /**
     * Public rooms remain static until a possible update triggered by the staff (cf update navigator command):
     * that's why they are only loaded during the launch of the server
     */
    private void loadPublicRooms() {
        this.publicRoomEntries = this.getNavigatorDao().getPublicRooms();
    }

    @Override
    public void load() {
        this.navigatorDao = new NavigatorDao();
        this.loadPublicRooms();
    }

    @Override
    public void unload() {
        this.publicRoomEntries.clear();
        this.navigatorDao = null;
    }
}
