package com.scuti.api.netty.messages;

public interface ServerMessage {
    public void writeString(String str);
    public void writeInt(int x);
    public void writeShort(int x);
    public void writeBool(boolean bool);
    public String getBodyString();
    public int getHeader();
}
