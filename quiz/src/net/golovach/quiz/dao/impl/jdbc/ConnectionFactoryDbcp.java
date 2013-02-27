package net.golovach.quiz.dao.impl.jdbc;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryDbcp implements ConnectionFactory {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/quiz_db";
    private static final String LOGIN = "username";
    private static final String PASSWORD = "password";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    private final BasicDataSource dataSource;

    public ConnectionFactoryDbcp() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(DRIVER_CLASS);
        ds.setUsername(LOGIN);
        ds.setPassword(PASSWORD);
        ds.setUrl(JDBC_URL);
        dataSource = ds;
    }

    public Connection newConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void close() throws SQLException {
        dataSource.close();
    }
}
