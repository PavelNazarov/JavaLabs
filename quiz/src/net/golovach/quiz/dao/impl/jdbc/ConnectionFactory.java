package net.golovach.quiz.dao.impl.jdbc;

import net.golovach.quiz.dao.exception.DaoSystemException;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {

    public Connection newConnection() throws SQLException;

    public void close() throws SQLException;
}
