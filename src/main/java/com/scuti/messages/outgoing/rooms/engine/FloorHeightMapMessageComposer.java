package com.scuti.messages.outgoing.rooms.engine;

import com.scuti.game.rooms.mapping.RoomModel;
import com.scuti.messages.outgoing.MessageComposer;

import java.util.Arrays;

public class FloorHeightMapMessageComposer extends MessageComposer {
    private final RoomModel model;

    public FloorHeightMapMessageComposer(RoomModel model) {
        this.getResponse().setHeader(470);
        this.model = model;
    }

    public RoomModel getModel() {
        return model;
    }

    @Override
    public void compose() {
        String[] lines = this.getModel().getHeightmap().split("\r");
        for (int y = 0; y < this.getModel().getMapSizeY(); y++) {
            String line = lines[y];
            for (int x = 0; x < this.getModel().getMapSizeX(); x++) {
                Character tile = line.charAt(x);
                if (x == this.getModel().getDoorX() && y == this.getModel().getDoorY()) {
                    this.getResponse().appendString(String.valueOf(this.getModel().getDoorZ()));
                } else {
                    this.getResponse().appendString(String.valueOf(tile));
                }
            }
            this.getResponse().appendString("\r");
        }
    }
}
