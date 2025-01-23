package services;

public interface IService {
    /**
     * Starts the service.
     */
    void start();

    /**
     * Stops the service.
     */
    void stop();

    /**
     * Checks if the service is launched.
     * @return state
     */
    boolean isActive();
}
