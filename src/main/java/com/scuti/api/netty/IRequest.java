package com.scuti.api.netty;

public interface IRequest {
    byte[] readBytes(int numBytes);
    byte[] readBytesFreezeCursor(int numBytes);
    byte[] readFixedValue();
    boolean popBase64Boolean();
    int popInt32();
    String popFixedString();
    int popFixedInt32();
    boolean popWiredBoolean();
    int popWiredInt32();
}
