package com.scuti.messages.outgoing.navigator;

import com.scuti.game.navigator.RoomNavigator;
import com.scuti.messages.outgoing.MessageComposer;

import java.util.List;

public class GuestRoomSearchResultMessageComposer extends MessageComposer {
    List<RoomNavigator> guestRooms;
    public GuestRoomSearchResultMessageComposer(List<RoomNavigator> guestRooms) {
        this.getResponse().setHeader(451);
        this.guestRooms = guestRooms;
    }

    public List<RoomNavigator> getGuestRooms() {
        return guestRooms;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(1);
        this.getResponse().appendStringWithBreak("");
        this.getResponse().appendInt32(this.getGuestRooms().size());

        for (RoomNavigator room: this.getGuestRooms()) {
            this.getResponse().appendInt32(room.getId());
            this.getResponse().appendBoolean(false); // event?
            this.getResponse().appendStringWithBreak(room.getCaption());
            this.getResponse().appendStringWithBreak(room.getOwnerName());
            this.getResponse().appendInt32(room.getState());
            this.getResponse().appendInt32(room.getUsersNow());
            this.getResponse().appendInt32(room.getCapacity());
            this.getResponse().appendStringWithBreak(room.getDescription());
            this.getResponse().appendInt32(0);
            this.getResponse().appendBoolean(true);
            this.getResponse().appendInt32(room.getScore());
            this.getResponse().appendInt32(room.getCategory());
            this.getResponse().appendStringWithBreak(room.getDateCreation());

            // tags
            this.getResponse().appendInt32(room.getTags().length);
            for (String tag: room.getTags()) {
                this.getResponse().appendStringWithBreak(tag);
            }

            // thumbnail
            this.getResponse().appendInt32(room.getBackgroundImageIcon());
            this.getResponse().appendInt32(room.getForegroundImageIcon());
            this.getResponse().appendInt32(1);
            this.getResponse().appendInt32(1);
            this.getResponse().appendInt32(3);

            this.getResponse().appendBoolean(true);
            this.getResponse().appendBoolean(false);

        }
    }
}
