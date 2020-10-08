package ru.vapima.butjet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCconnection {
    static Connection conn;

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:8889/butjet?serverTimezone=Europe/Moscow";
        String username = "butjet";
        String password = "12345678";
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }


}
