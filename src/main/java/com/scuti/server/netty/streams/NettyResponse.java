package com.scuti.server.netty.streams;

import com.scuti.api.netty.messages.ServerMessage;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

public class NettyResponse implements ServerMessage {

    private short id;
    private ByteBuf buffer;

    public NettyResponse(short header, ByteBuf buffer) {
        this.id = header;
        this.buffer = buffer;
        this.buffer.writeInt(-1);
        this.buffer.writeShort(id);
    }

    @Override
    public void writeString(String s) {
        buffer.writeShort(s.length());
        buffer.writeBytes(s.getBytes());
    }

    @Override
    public void writeInt(int x) {
        buffer.writeInt(x);
    }

    @Override
    public void writeShort(int x) {
        buffer.writeShort((short)x);
    }

    @Override
    public void writeBool(boolean bool) {
        buffer.writeBoolean(bool);
    }

    @Override
    public String getBodyString() {

        String str = this.buffer.toString(Charset.defaultCharset());

        for (int i = 0; i < 14; i++) {
            str = str.replace(Character.toString((char)i), "[" + i + "]");
        }

        return str;
    }

    @Override
    public int getHeader() {
        return this.id;
    }

    public boolean hasLength() {
        return (this.buffer.getInt(0) > -1);

    }
}
