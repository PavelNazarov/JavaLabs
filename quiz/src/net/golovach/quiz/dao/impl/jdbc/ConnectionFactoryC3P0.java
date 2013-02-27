package net.golovach.quiz.dao.impl.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryC3P0 implements ConnectionFactory {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/quiz_db";
    private static final String LOGIN = "username";
    private static final String PASSWORD = "password";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    private final ComboPooledDataSource dataSource;

    public ConnectionFactoryC3P0() throws SQLException {
        try {
            dataSource = new ComboPooledDataSource();
            // db
            dataSource.setDriverClass(DRIVER_CLASS);
            dataSource.setJdbcUrl(JDBC_URL);
            dataSource.setUser(LOGIN);
            dataSource.setPassword(PASSWORD);
            // connection pool
            dataSource.setMinPoolSize(1);
            dataSource.setAcquireIncrement(1);
            dataSource.setMaxPoolSize(20);
            // statement pool
            dataSource.setMaxStatements(180);
            dataSource.setMaxStatementsPerConnection(10);
        } catch (PropertyVetoException e) {
            throw new SQLException("Exception during configuring c3p0", e);
        }
    }

    public Connection newConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void close() throws SQLException {
        dataSource.close();
    }
}
