package com.scuti.server.netty.streams;

import com.scuti.api.netty.messages.ClientMessage;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

public class NettyRequest implements ClientMessage {

    final private short header;
    final private int length;

    final public ByteBuf buffer;

    public NettyRequest(int length, ByteBuf buffer) {
        this.buffer = buffer;
        this.header = buffer.readShort();
        this.length = length;
    }

    @Override
    public int readInt() {
        try {
            return this.buffer.readInt();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public boolean readIntAsBool() {
        try {
            return this.buffer.readInt() == 1;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean readBoolean() {
        try {
            return this.buffer.readByte() == 1;
        } catch (Exception e)    {
            return false;
        }
    }

    @Override
    public String readString() {
        try {
            int length = this.buffer.readShort();
            byte[] data = this.readBytes(this.length);

            return new String(data);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public byte[] readBytes(int len) {
        try {
            byte[] payload = new byte[len];
            this.buffer.readBytes(payload);
            return payload;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getMessageBody() {
        String consoleText = this.buffer.toString(Charset.defaultCharset());

        for (int i = 0; i < 13; i++) {
            consoleText = consoleText.replace(Character.toString((char)i), "[" + i + "]");
        }

        return consoleText;
    }

    @Override
    public int getMessageId() {
        return this.header;
    }

    @Override
    public int getLength() {
        return this.length;
    }

    public ByteBuf getBuffer() {
        return buffer;
    }
}
