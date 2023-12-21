package com.scuti.server.netty.streams;

import com.scuti.encoding.WireEncoding;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class NettyResponse {

    private int id;
    private final ArrayList<Byte> body = new ArrayList<Byte>();

    public NettyResponse(int message_id) {
        this.id = message_id;
    }

    public void clear() {
        this.body.clear();
    }

    public void appendByte(byte b) {
        this.body.add(b);
    }

    public void appendBytes(byte[] data) {
        if (data != null) {
            for (byte datum : data) {
                this.appendByte(datum);
            }
        }
    }

    public void appendBoolean(boolean bool) {
        if (bool) {
            this.appendInt32(WireEncoding.POSITIVE);
        } else {
            this.appendInt32(WireEncoding.NEGATIVE);
        }
    }

    public void appendInt32(int i) {
        this.appendBytes(WireEncoding.encodeInt32(i));
    }

    public void appendString(String s) {
        if (s.length() > 0) {
            this.appendBytes(s.getBytes(StandardCharsets.UTF_8));
        }
    }
}
