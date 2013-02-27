package net.golovach.quiz.dao.impl.jdbc;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryProxol implements ConnectionFactory {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/quiz_db";
    private static final String LOGIN = "username";
    private static final String PASSWORD = "password";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    public Connection newConnection() throws SQLException {
        return DriverManager.getConnection("proxool.production");
    }

    public ConnectionFactoryProxol() throws SQLException {
        try {
            Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("There is no Proxool driver in classpath", e);
        }
        Properties info = new Properties();
        info.setProperty("proxool.maximum-connection-count", "10");
        info.setProperty("proxool.house-keeping-test-sql", "select CURRENT_DATE");
        info.setProperty("user", LOGIN);
        info.setProperty("password", PASSWORD);
        String alias = "production";
        String driverClass = DRIVER_CLASS;
        String driverUrl = JDBC_URL;
        String url = "proxool." + alias + ":" + driverClass + ":" + driverUrl;
        try {
            ProxoolFacade.registerConnectionPool(url, info);
        } catch (ProxoolException e) {
            throw new SQLException("Some error configuring Proxool connection pool", e);
        }
    }

    @Override
    public void close() throws SQLException {
        ProxoolFacade.shutdown(0);
    }
}
