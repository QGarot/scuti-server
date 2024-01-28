package com.scuti.messages.outgoing.navigator;

import com.scuti.game.rooms.Room;
import com.scuti.messages.outgoing.MessageComposer;

public class GetGuestRoomResultComposer extends MessageComposer {
    private final boolean enterRoom;
    private final Room room;
    private final boolean roomForward;
    private final boolean staffPick;

    public GetGuestRoomResultComposer(boolean enterRoom, Room room, boolean roomForward, boolean staffPick) {
        this.getResponse().setHeader(454);
        this.enterRoom = enterRoom;
        this.room = room;
        this.roomForward = roomForward;
        this.staffPick = staffPick;
    }

    public boolean isEnterRoom() {
        return enterRoom;
    }

    public Room getRoom() {
        return room;
    }

    public boolean roomForward() {
        return roomForward;
    }

    public boolean isStaffPick() {
        return staffPick;
    }

    @Override
    public void compose() {
        this.getResponse().appendBoolean(this.isEnterRoom());

        // serialize room details
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

        this.getResponse().appendBoolean(this.roomForward());
        this.getResponse().appendBoolean(this.isStaffPick());
    }
}
