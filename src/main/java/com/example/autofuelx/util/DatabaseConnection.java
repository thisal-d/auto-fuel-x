package com.example.autofuelx.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    // database URL, username and password
    static String URL = "jdbc:sqlserver://localhost:1433;databaseName=AutoFuelX;encrypt=false;";
    static String USER = "sa";
    static String PASSWORD = "789";
    static Connection conn = null;

    // method to return instance
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) { // check if connection is closed
                createConnection();
            }
        } catch (Exception e) {
            e.printStackTrace();
            createConnection(); //  recreate connection if check failed
        }
        return conn;
    }

    // method to create instance
    private static void createConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database connection failed!");
        }
    }
}
