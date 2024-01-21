package com.scuti.messages.outgoing.navigator;

import com.scuti.game.rooms.Room;
import com.scuti.messages.outgoing.MessageComposer;

import java.util.List;

public class GuestRoomSearchResultMessageComposer extends MessageComposer {
    private final List<Room> guestRooms;
    public GuestRoomSearchResultMessageComposer(List<Room> guestRooms) {
        this.getResponse().setHeader(451);
        this.guestRooms = guestRooms;
    }

    public List<Room> getGuestRooms() {
        return guestRooms;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(1);
        this.getResponse().appendStringWithBreak("");
        this.getResponse().appendInt32(this.getGuestRooms().size());

        for (Room room: this.getGuestRooms()) {
            this.getResponse().appendInt32(room.getDetails().getId());
            this.getResponse().appendBoolean(true); // event?
            this.getResponse().appendStringWithBreak(room.getDetails().getCaption());
            this.getResponse().appendStringWithBreak(room.getDetails().getOwnerName());
            this.getResponse().appendInt32(room.getDetails().getState());
            this.getResponse().appendInt32(room.getDetails().getUsersNow());
            this.getResponse().appendInt32(room.getDetails().getUsersMax());
            this.getResponse().appendStringWithBreak(room.getDetails().getDescription());
            this.getResponse().appendInt32(0);
            this.getResponse().appendBoolean(room.getDetails().isTradingAllowed());
            this.getResponse().appendInt32(room.getDetails().getScore());
            this.getResponse().appendInt32(room.getDetails().getCategory());
            this.getResponse().appendStringWithBreak(room.getDetails().getDateCreation());

            // tags
            String[] tags = room.getDetails().getTags().split(";");
            this.getResponse().appendInt32(tags.length);
            for (String tag: tags) {
                this.getResponse().appendStringWithBreak(tag);
            }


            // thumbnail
            this.getResponse().appendInt32(room.getThumbnail().getBackgroundImageIcon());
            this.getResponse().appendInt32(room.getThumbnail().getForegroundImageIcon());
            this.getResponse().appendInt32(room.getThumbnail().getItemCount());
            this.getResponse().appendInt32(room.getThumbnail().getItemPosition());
            this.getResponse().appendInt32(room.getThumbnail().getItemImageId());

            this.getResponse().appendBoolean(room.getDetails().arePetsAllowed());
            this.getResponse().appendBoolean(room.getDetails().isDisplayRoomEntryAd());
        }
    }
}
