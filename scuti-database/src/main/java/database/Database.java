package database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import logger.Logger;

import java.sql.*;

public class Database implements IDatabase {

    private final String host;

    private final String username;

    private final String password;

    private final String name;

    private final HikariDataSource dataSource;

    private final HikariConfig config;

    public Database(String host, String username, String password, String name) throws Exception {
        this.host = host;
        this.username = username;
        this.password = password;
        this.name = name;

        this.config = new HikariConfig();
        this.getConfig().setDriverClassName("com.mysql.cj.jdbc.Driver");
        this.getConfig().setJdbcUrl("jdbc:mysql://" + this.getHost() + ":3306/" + this.getName());
        this.getConfig().setUsername(this.getUsername());
        this.getConfig().setPassword(this.getPassword());
        this.dataSource = new HikariDataSource(this.getConfig());
    }

    /**
     * Returns the hikari configuration.
     * @return config
     */
    private HikariConfig getConfig() {
        return config;
    }

    /**
     * Returns the hikari data source.
     * @return datasource
     */
    private HikariDataSource getDataSource() {
        return dataSource;
    }

    @Override
    public boolean isValid() {
        IDatabase instance;
        boolean available;

        try {
            instance = new Database("localhost", "root", "", "scuti");
            // Test connection
            Connection connection = instance.getConnection();
            connection.close();
            Logger.logInfo("Connected to database!");
            available = true;
        } catch (Exception e) {
            Logger.logError("Loading database error!");
            Logger.logError(e.getMessage());
            available = false;
            //System.exit(0);
        }

        return available;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.getDataSource().getConnection();
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
