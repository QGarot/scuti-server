package com.scuti.messages.outgoing.navigator;

import com.scuti.game.components.navigator.entries.PublicRoomEntry;
import com.scuti.messages.outgoing.MessageComposer;

import java.util.List;

public class OfficialRoomsMessageComposer extends MessageComposer {
    private final List<PublicRoomEntry> publicRooms;

    public OfficialRoomsMessageComposer(List<PublicRoomEntry> publicRooms) {
        this.getResponse().setHeader(450);
        this.publicRooms = publicRooms;
    }

    public List<PublicRoomEntry> getPublicRooms() {
        return publicRooms;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(this.getPublicRooms().size()); //nb de lieux publics
        for (PublicRoomEntry entry: this.getPublicRooms()) {
            this.getResponse().appendInt32(entry.getId()); // index
            this.getResponse().appendStringWithBreak(entry.getCaption()); // popup caption
            this.getResponse().appendStringWithBreak(entry.getDescription()); // popup description
            this.getResponse().appendInt32(0); // type/show details
            this.getResponse().appendStringWithBreak(entry.getCaption()); // pic text
            this.getResponse().appendStringWithBreak(entry.getImage()); // picRef - relative path from c_images folder
            this.getResponse().appendInt32(0); // is recommended/folder id
            this.getResponse().appendInt32(0); // users now (useless)
            this.getResponse().appendInt32(3); // type
            //-- 3 --
            this.getResponse().appendStringWithBreak(""); // unit property set
            this.getResponse().appendInt32(1337); // unit port
            this.getResponse().appendBoolean(false); // world id
            this.getResponse().appendStringWithBreak(""); // CCTs (cast libs)
            this.getResponse().appendInt32(entry.getUsersMax()); // users max
            this.getResponse().appendInt32(entry.getRoomId()); // node id
        }

    }
}
