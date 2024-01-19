package com.scuti.messages.outgoing.navigator;

import com.scuti.game.navigator.PublicRoomEntry;
import com.scuti.messages.outgoing.MessageComposer;

import java.util.List;

public class OfficialRoomsMessageComposer extends MessageComposer {
    private List<PublicRoomEntry> publicRooms;

    public OfficialRoomsMessageComposer(List<PublicRoomEntry> publicRooms) {
        this.getResponse().setHeader(450);
        this.publicRooms = publicRooms;
    }

    public List<PublicRoomEntry> getPublicRooms() {
        return publicRooms;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(this.getPublicRooms().size());
        for (PublicRoomEntry publicRoom: this.getPublicRooms()) {
            this.getResponse().appendInt32(publicRoom.getId());
            this.getResponse().appendStringWithBreak(publicRoom.getCaption());
            this.getResponse().appendStringWithBreak(publicRoom.getDescription());
            this.getResponse().appendInt32(0); // type
            this.getResponse().appendStringWithBreak(publicRoom.getCaption());
            this.getResponse().appendStringWithBreak(publicRoom.getImage());
            this.getResponse().appendInt32(0); // is recommended
            this.getResponse().appendInt32(5); // users now
            this.getResponse().appendInt32(3); // ?
            this.getResponse().appendStringWithBreak(publicRoom.getImage());
            this.getResponse().appendInt32(1337);
            this.getResponse().appendBoolean(true);
            this.getResponse().appendStringWithBreak(""); // CCTs
            this.getResponse().appendInt32(50); // users max
            this.getResponse().appendInt32(publicRoom.getRoomId());
        }
    }
}
