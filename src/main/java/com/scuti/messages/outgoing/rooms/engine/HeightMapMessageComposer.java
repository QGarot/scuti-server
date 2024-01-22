package com.scuti.messages.outgoing.rooms.engine;

import com.scuti.messages.outgoing.MessageComposer;

public class HeightMapMessageComposer extends MessageComposer {
    private final String heightMap;

    public HeightMapMessageComposer(String heightMap) {
        this.getResponse().setHeader(31);
        this.heightMap = heightMap;
    }

    public String getHeightMap() {
        return heightMap;
    }

    @Override
    public void compose() {
        this.getResponse().appendString(this.getHeightMap());
    }
}
