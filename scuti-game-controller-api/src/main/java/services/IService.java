package services;

public interface IService {
    /**
     * Starts the service. Performs all internal process.
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
