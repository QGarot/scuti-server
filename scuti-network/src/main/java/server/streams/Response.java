package server.streams;

import server.codec.encoding.Base64Encoding;
import server.codec.encoding.WireEncoding;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Response implements IResponse {

    private final int header;
    private final ArrayList<Byte> body;

    public Response(int header) {
        this.header = header;
        this.body = new ArrayList<>();
    }

    private void appendByte(byte b) {
        this.body.add(b);
    }

    private void appendBytes(byte[] data) {
        if (data != null) {
            for (byte datum : data) {
                this.appendByte(datum);
            }
        }
    }

    @Override
    public void appendBoolean(boolean bool) {
        if (bool) {
            this.appendByte(WireEncoding.POSITIVE);
        } else {
            this.appendByte(WireEncoding.NEGATIVE);
        }
    }

    @Override
    public void appendInt32(int i) {
        this.appendBytes(WireEncoding.encodeInt32(i));
    }

    @Override
    public void appendString(String s) {
        if (!s.isEmpty()) {
            this.appendBytes(s.getBytes(StandardCharsets.UTF_8));
        }
    }

    @Override
    public void appendStringWithBreak(String s) {
        this.appendString(s);
        this.appendByte((byte) 2);
    }

    @Override
    public byte[] getBytes() {
        byte[] data = new byte[this.body.size() + 3];
        byte[] header = Base64Encoding.encodeInt32(this.header, 2);
        data[0] = header[0];
        data[1] = header[1];
        for (int i = 0; i < this.body.size(); i++) {
            data[i + 2] = this.body.get(i);
        }
        data[data.length - 1] = 1;
        return data;
    }

    @Override
    public int getHeader() {
        return this.header;
    }
}
