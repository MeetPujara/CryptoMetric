package com.cryptometric.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = ConfigLoader.get("DB_URL");
    private static final String USER = ConfigLoader.get("DB_USER");
    private static final String PASS = ConfigLoader.get("DB_PASS");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}