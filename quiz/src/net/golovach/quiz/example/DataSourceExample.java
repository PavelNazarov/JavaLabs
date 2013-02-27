package net.golovach.quiz.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceExample {
    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/quiz_db";
    public static final String LOGIN = "username";
    public static final String PASSWORD = "password";

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(JDBC_URL, LOGIN, PASSWORD);
        System.out.println(conn.getClientInfo());
    }
}

