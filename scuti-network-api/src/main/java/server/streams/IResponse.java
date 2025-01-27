package server.streams;

/**
 * An outgoing message abstraction representing a writable object.
 */
public interface IResponse {
    void appendBoolean(boolean bool);
    void appendInt32(int i);
    void appendString(String s);
    void appendStringWithBreak(String s);
    byte[] getBytes();
    int getHeader();
}
