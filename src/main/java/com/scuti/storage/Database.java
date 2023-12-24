package com.scuti.storage;

import com.mysql.cj.jdbc.Driver;
import com.scuti.util.logger.Logger;

import java.sql.*;

public class Database {
    private static Database instance;
    private String host;
    private String username;
    private String password;
    private String name;
    private Connection connection;

    public Database(String host, String username, String password, String name) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.name = name;
        try {
            DriverManager.registerDriver(new Driver());
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.name + "?user=" + this.username + "&password=" + this.password);
        } catch (Exception e) {
            Logger.logError(e.getMessage());
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database("localhost", "root", "", "scuti");
        }

        return instance;
    }

    public void test() {
        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement("SELECT * FROM users");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
            }
        } catch (Exception e) {
            Logger.logError(e.getMessage());
        }
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
