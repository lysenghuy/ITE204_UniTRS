package com.unitrs.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {

    private static final Properties properties = new Properties();

    static {
        try (InputStream is = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (is != null) {
                properties.load(is);
            }
            Class.forName(properties.getProperty("db.driver", "com.mysql.cj.jdbc.Driver"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Database connected: " + conn.getCatalog());
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
}