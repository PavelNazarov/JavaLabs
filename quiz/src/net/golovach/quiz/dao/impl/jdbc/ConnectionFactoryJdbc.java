package net.golovach.quiz.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryJdbc implements ConnectionFactory {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/quiz_db";
    private static final String LOGIN = "username";
    private static final String PASSWORD = "password";

    public Connection newConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, LOGIN, PASSWORD);
    }

    @Override
    public void close() throws SQLException {
        // nothing to close
    }
}
