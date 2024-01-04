package com.scuti.server.netty.streams;

import com.scuti.server.encoding.Base64Encoding;
import com.scuti.server.encoding.WireEncoding;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class NettyResponse {

    private int id;
    private final ArrayList<Byte> body = new ArrayList<Byte>();

    public NettyResponse() {

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
            this.appendByte(WireEncoding.POSITIVE);
        } else {
            this.appendByte(WireEncoding.NEGATIVE);
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

    public void appendStringWithBreak(String s) {
        this.appendString(s);
        this.appendByte((byte) 2);
    }

    public byte[] getBytes() {
        byte[] data = new byte[this.body.size() + 3];
        byte[] header = Base64Encoding.encodeInt32(this.id, 2);
        data[0] = header[0];
        data[1] = header[1];
        for (int i = 0; i < this.body.size(); i++) {
            data[i + 2] = this.body.get(i);
        }
        data[data.length - 1] = 1;
        return data;
    }

    public int getHeader() {
        return this.id;
    }

    public void setHeader(int header) {
        this.id = header;
    }
}
