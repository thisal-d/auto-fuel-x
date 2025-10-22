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
        if (conn == null) {
            createConnection();
        }
        return conn;
    }

    // method to create instance
    private static void createConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // connect to DB
            // create object and assign to conn variable
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
