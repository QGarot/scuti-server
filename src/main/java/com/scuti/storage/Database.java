package com.scuti.storage;

import com.scuti.util.logger.Logger;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class Database {
    private static Database instance;
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
        //this.getConfig().setJdbcUrl("jdbc:mariadb://" + this.getHost() + ":3306/" + this.getName());
        this.getConfig().setUsername(this.getUsername());
        this.getConfig().setPassword(this.getPassword());
        this.dataSource = new HikariDataSource(this.getConfig());
    }

    /**
     * Get hikari configuration
     * @return config:
     */
    public HikariConfig getConfig() {
        return config;
    }

    /**
     * Get hikari data source
     * @return datasource:
     */
    public HikariDataSource getDataSource() {
        return dataSource;
    }

    /**
     * Return database object after testing it
     * @return instance:
     */
    public static Database getInstance() {
        if (instance == null) {
            try {
                instance = new Database("localhost", "root", "", "scuti");
                // Test connection
                Connection connection = instance.getConnection();
                connection.close();
                Logger.logInfo("Connected to database!");
            } catch (Exception e) {
                Logger.logError("Loading database error!");
                Logger.logError(e.getMessage());
                System.exit(0);
            }
        }

        return instance;
    }

    /**
     * Return database connection
     * @return connection:
     */
    public Connection getConnection() throws SQLException {
        return this.getDataSource().getConnection();
    }

    /**
     * Get the database host
     * @return host:
     */
    public String getHost() {
        return host;
    }

    /**
     * Get the database username
     * @return username:
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the database name
     * @return name:
     */
    public String getName() {
        return name;
    }

    /**
     * Get the database password
     * @return password:
     */
    public String getPassword() {
        return password;
    }
}
