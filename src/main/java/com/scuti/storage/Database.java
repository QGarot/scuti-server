package com.scuti.storage;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private String host;
    private String username;
    private String password;
    private String name;
    private Connection connection;

    public Database(String host, String username, String password, String name) throws SQLException {
        this.host = host;
        this.username = username;
        this.password = password;
        this.name = name;
        DriverManager.registerDriver(new Driver());
        this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.name + "?user=" + this.username + "&password=" + this.password);
    }

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database("localhost", "root", "", "scuti");
        }

        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() throws SQLException {
        this.getConnection().close();
    }

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
