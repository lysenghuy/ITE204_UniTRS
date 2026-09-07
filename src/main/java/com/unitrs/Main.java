package com.unitrs;

import com.unitrs.utils.DatabaseUtils;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting application...");
        try (Connection connection = DatabaseUtils.getConnection()) {
            System.out.println("Database connected successfully: " + connection.getCatalog());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
