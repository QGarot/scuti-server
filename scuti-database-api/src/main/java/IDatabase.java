import java.sql.Connection;
import java.sql.SQLException;

/**
 * An interface representing the database linked to the server.
 */
public interface IDatabase {
    /**
     * Returns the database connection.
     * @return connection
     */
    Connection getConnection() throws SQLException;

    /**
     * Returns the database host.
     * @return host
     */
    String getHost();

    /**
     * Returns the database username.
     * @return username
     */
    String getUsername();

    /**
     * Returns the database name.
     * @return name
     */
    String getName();

    /**
     * Returns the database password.
     * @return password
     */
    String getPassword();

    /**
     * Returns whether the database access is correct.
     * @return correct database access
     */
    boolean isValid();
}
