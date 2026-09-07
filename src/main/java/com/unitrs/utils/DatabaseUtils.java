package com.unitrs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtils {

    static {
        try {
            Properties properties = CredentialsLoader.loadProperties();
            Class.forName(properties.getProperty("db.driver", "com.mysql.cj.jdbc.Driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to initialize database driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Properties properties = CredentialsLoader.loadProperties();

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        Connection connection = DriverManager.getConnection(url, user, password);

        if (!connection.isValid(2)) {
            throw new SQLException("Database connection is not valid.");
        }

        return connection;
    }
}
