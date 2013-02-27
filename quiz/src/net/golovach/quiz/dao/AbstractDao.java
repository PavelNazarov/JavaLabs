package net.golovach.quiz.dao;

import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.tx.TransactionManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDao<T> {

    protected Connection getConnection() throws DaoSystemException {
        return TransactionManagerImpl.getConnection();
    }

    protected Connection getSerializableConnection() throws DaoSystemException {
        try {
            Connection result = getConnection();
            result.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            result.setAutoCommit(false);
            return result;
        } catch (SQLException e) {
            throw new DaoSystemException("Can't create connection", e);
        }
    }
}

