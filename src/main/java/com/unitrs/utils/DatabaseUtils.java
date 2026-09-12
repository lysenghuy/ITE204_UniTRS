package com.unitrs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {

            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {

            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {

            }
        }
    }
}
