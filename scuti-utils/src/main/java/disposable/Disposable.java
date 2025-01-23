package disposable;

public interface Disposable {
    /**
     * Disposes the object.
     */
    void dispose();

    /**
     * Checks if the object is disposed.
     * @return boolean
     */
    boolean isDisposed();
}
