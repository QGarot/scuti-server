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

    public Database(String host, String username, String password, String name) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database("localhost", "root", "", "scuti");
            Connection connection;
            try {
                connection = instance.getConnection();
                connection.close();
            } catch (SQLException e) {
                Logger.logError(e.getMessage());
                System.exit(0);
            }
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

    public Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://" + this.getHost() + "/" + this.getName() + "?user=" + this.getUsername() + "&password=" + this.getPassword());
        return connection;
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
