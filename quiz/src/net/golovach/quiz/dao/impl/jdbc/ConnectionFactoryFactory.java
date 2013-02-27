package net.golovach.quiz.dao.impl.jdbc;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ConnectionFactoryFactory {
    public static enum FactoryType {RAW, C3P0, PROXOOL, DBCP, VAHA_CONN}

    private static FactoryType currentType = FactoryType.RAW;
    private static List<ConnectionFactory> allFactories = new LinkedList<ConnectionFactory>();

    public static synchronized void setType(FactoryType type) {
        currentType = type;
    }

    public synchronized static ConnectionFactory newConnectionFactory() {
        ConnectionFactory result;

        try {
            switch (currentType) {
                case RAW:
                    result = new ConnectionFactoryJdbc();
                    break;
                case C3P0:
                    result = new ConnectionFactoryC3P0();
                    break;
                case DBCP:
                    result = new ConnectionFactoryDbcp();
                    break;
                case PROXOOL:
                    result = new ConnectionFactoryProxol();
                    break;
                case VAHA_CONN:
                    result = new ConnectionFactoryProxol();
                    break;
                default:
                    throw new RuntimeException("Never here! Now only " + Arrays.toString(FactoryType.values()) + " allowed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static synchronized void close() {
        for (ConnectionFactory factory : allFactories) {
            try {
                factory.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
