package server.streams;

/**
 * An incoming message abstraction representing a readable object.
 */
public interface IRequest {
    int getHeader();
    boolean popBase64Boolean();
    boolean popWiredBoolean();
    int popInt32();
    int popFixedInt32();
    int popWiredInt32();
    String popFixedString();
}
