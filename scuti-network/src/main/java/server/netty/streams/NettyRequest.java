package server.netty.streams;

import server.encoding.Base64Encoding;
import server.encoding.WireEncoding;
import server.streams.IRequest;

import java.nio.charset.StandardCharsets;

public class NettyRequest implements IRequest {

    private final byte[] body;
    private final int messageId;
    private int pointer;

    public NettyRequest(int messageId, byte[] body) {
        if (body == null) {
            body = new byte[0];
        }

        this.messageId = messageId;
        this.body = body;
        this.pointer = 0;
    }

    public int getHeader() {
        return this.messageId;
    }

    public int getRemainingLength() {
        return this.body.length - this.pointer;
    }

    public byte[] readBytes(int bytes) {
        if (bytes > this.getRemainingLength()) {
            bytes = this.getRemainingLength();
        }
        byte[] bzData = new byte[bytes];
        for (int i = 0 ; i < bytes ; i++) {
            bzData[i] = this.body[this.pointer++];
        }
        return bzData;
    }

    public byte[] plainReadBytes(int bytes) {
        if (bytes > this.getRemainingLength()) {
            bytes = this.getRemainingLength();
        }

        byte[] bzData = new byte[bytes];
        int index = 0;
        for (int i = this.pointer ; index < bytes; i++)
        {
            bzData[index] = this.body[i];
            index++;
        }

        return bzData;
    }
    
    public byte[] readFixedValue() {
        int bytes = Base64Encoding.decodeInt32(this.readBytes(2));
        return this.readBytes(bytes);
    }

    @Override
    public boolean popBase64Boolean() {
        return (this.getRemainingLength() > 0 && this.body[this.pointer++] == 0x41);
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
        return (this.getRemainingLength() > 0 && this.body[this.pointer++] == WireEncoding.POSITIVE);
    }

    @Override
    public int popWiredInt32() {
        if (this.getRemainingLength() < 1) {
            return 0;
        }

        byte[] bzData = this.plainReadBytes(WireEncoding.MAX_INTEGER_BYTE_AMOUNT);
        int totalBytes = 0;
        int i = WireEncoding.DecodeInt32(bzData, totalBytes);
        totalBytes = WireEncoding.currentTotalBytes;

        this.pointer += totalBytes;
        return i;
    }
}
