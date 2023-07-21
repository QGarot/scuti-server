package com.scuti.api.netty.messages;

public interface ClientMessage {
    public int readInt();
    public boolean readIntAsBool();
    public boolean readBoolean();
    public String readString();
    public byte[] readBytes(int len);
    public String getMessageBody();
    public int getMessageId();
    public int getLength();
}
