package net.golovach.quiz.dao.tx;

import net.golovach.quiz.dao.impl.jdbc.ConnectionFactory;
import net.golovach.quiz.dao.impl.jdbc.ConnectionFactoryFactory;
import net.golovach.quiz.dao.impl.jdbc.JdbcUtils;

import java.sql.Connection;
import java.util.concurrent.Callable;

public class TransactionManagerImpl implements TransactionManager {
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();
    private static final ConnectionFactory factory = ConnectionFactoryFactory.newConnectionFactory();

    @Override
    public <T> T doInTransaction(Callable<T> unitOfWork) throws Exception {
        Connection connection = factory.newConnection();
        connectionHolder.set(connection);
        try {
            T result = unitOfWork.call();
            connection.commit();
            return result;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            JdbcUtils.closeQuietly(connection);
            connectionHolder.remove();
        }
    }

    public static Connection getConnection() {
        return connectionHolder.get();
    }
}

//class ThreadLoc<T> {
//    private final Map<Thread, T> map = new HashMap<Thread, T>();
//    public void set(T elem) {
//        map.put(Thread.currentThread(), elem);
//    }
//    public T get() {
//        return map.get(Thread.currentThread());
//    }
//}