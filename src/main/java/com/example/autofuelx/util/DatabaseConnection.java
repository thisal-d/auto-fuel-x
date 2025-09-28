package com.example.autofuelx.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    // Database URL, username and password
    static String URL = "jdbc:sqlserver://localhost:1433;databaseName=AutoFuelX;encrypt=false;";
    static String USER = "sa";
    static String PASSWORD = "789";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Connect to DB
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
