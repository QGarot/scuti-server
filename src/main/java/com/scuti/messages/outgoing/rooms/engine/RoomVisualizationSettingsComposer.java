package com.scuti.messages.outgoing.rooms.engine;

import com.scuti.messages.outgoing.MessageComposer;

public class RoomVisualizationSettingsComposer extends MessageComposer {
    private final boolean hideWall;
    private final int wallThick;
    private final int floorThick;

    public RoomVisualizationSettingsComposer(boolean hideWall, int wallThick, int floorThick) {
        this.getResponse().setHeader(472);
        this.hideWall = hideWall;
        this.wallThick = wallThick;
        this.floorThick = floorThick;
    }

    public boolean hideWall() {
        return hideWall;
    }

    public int getWallThick() {
        return wallThick;
    }

    public int getFloorThick() {
        return floorThick;
    }

    @Override
    public void compose() {
        this.getResponse().appendBoolean(this.hideWall());
        this.getResponse().appendInt32(this.getWallThick());
        this.getResponse().appendInt32(this.getFloorThick());
    }
}
