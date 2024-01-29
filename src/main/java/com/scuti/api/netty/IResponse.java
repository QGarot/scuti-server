package com.scuti.api.netty;

public interface IResponse {
    void appendByte(byte b);
    void appendBytes(byte[] data);
    void appendBoolean(boolean bool);
    void appendInt32(int i);
    void appendString(String s);
    void appendStringWithBreak(String s);
    void setHeader(int header);
}
