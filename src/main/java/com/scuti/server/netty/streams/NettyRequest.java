package com.scuti.server.netty.streams;

import com.scuti.api.netty.IRequest;
import com.scuti.server.encoding.Base64Encoding;
import com.scuti.server.encoding.WireEncoding;

import java.nio.charset.StandardCharsets;

public class NettyRequest implements IRequest {

    private final byte[] body;
    private final int messageId;
    private int pointer;
    private final int remainingContent;

    public NettyRequest(int messageId, byte[] body) {
        if (body == null) {
            body = new byte[0];
        }

        this.messageId = messageId;
        this.body = body;
        this.pointer = 0;
        this.remainingContent = this.body.length - this.pointer;
    }

    public int getHeader() {
        return this.messageId;
    }

    public void reset() {
        this.pointer = 0;
    }

    public void advancePointer(int i) {
        this.pointer = this.pointer + i;
    }

    public String getContentString() {
        return new String(this.body, StandardCharsets.UTF_8);
    }

    @Override
    public byte[] readBytes(int numBytes) {
        if (numBytes > this.remainingContent) {
            numBytes = this.remainingContent;
        }
        byte[] bzData = new byte[numBytes];
        for (int i = 0 ; i < numBytes ; i++) {
            bzData[i] = this.body[this.pointer++];
        }
        return bzData;
    }

    @Override
    public byte[] readBytesFreezeCursor(int numBytes) {
        if (numBytes > this.remainingContent) {
            numBytes = this.remainingContent;
        }

        byte[] bzData = new byte[numBytes];
        for (int x = 0, y = this.pointer; x < numBytes; x++, y++)
        {
            // ??
            bzData[x] = this.body[y];
        }

        return bzData;
    }

    @Override
    public byte[] readFixedValue() {
        int length = Base64Encoding.decodeInt32(this.readBytes(2));
        return this.readBytes(length);
    }

    @Override
    public boolean popBase64Boolean() {
        return (this.remainingContent > 0 && this.body[this.pointer++] == 65);
    }

    @Override
    public int popInt32() {
        return Base64Encoding.decodeInt32(this.readBytes(2));
    }

    @Override
    public String popFixedString() {
        return new String(this.readFixedValue(), StandardCharsets.UTF_8);
    }

    @Override
    public int popFixedInt32() {
        String s = new String(this.readFixedValue(), StandardCharsets.US_ASCII);
        return Integer.parseInt(s);
    }

    @Override
    public boolean popWiredBoolean() {
        return (this.remainingContent > 0 && this.body[this.pointer++] == WireEncoding.POSITIVE);
    }

    @Override
    public int popWiredInt32() {
        if (this.remainingContent == 0) {
            return 0;
        }

        byte[] bzData = this.readBytesFreezeCursor(WireEncoding.MAX_INTEGER_BYTE_AMOUNT);
        int totalBytes = 0;
        int i = WireEncoding.DecodeInt32(bzData, totalBytes);
        totalBytes = WireEncoding.currentTotalBytes;

        this.pointer = this.pointer + totalBytes;
        return i;
    }
}
