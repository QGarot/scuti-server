package com.scuti.messages.outgoing.rooms.engine;

import com.scuti.messages.outgoing.MessageComposer;

public class RoomPropertyMessageComposer extends MessageComposer {
    private final String roomPropertyType;
    private final String roomProperty;

    public RoomPropertyMessageComposer(String roomPropertyType, String roomProperty) {
        this.getResponse().setHeader(46);
        this.roomPropertyType = roomPropertyType; // floor, wallpaper, landscape, landscapeanim
        this.roomProperty = roomProperty;
    }

    public String getRoomPropertyType() {
        return roomPropertyType;
    }

    public String getRoomProperty() {
        return roomProperty;
    }

    @Override
    public void compose() {
        this.getResponse().appendStringWithBreak(this.getRoomPropertyType());
        this.getResponse().appendStringWithBreak(this.getRoomProperty());
    }
}
