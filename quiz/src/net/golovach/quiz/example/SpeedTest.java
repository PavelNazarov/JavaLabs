package net.golovach.quiz.example;

import net.golovach.quiz.dao.UserDao;
import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.impl.UserDaoImpl;
import net.golovach.quiz.dao.impl.jdbc.ConnectionFactoryFactory;
import net.golovach.quiz.entity.User;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpeedTest {
    private static final int COUNT = 100;

    public static void main(String[] args) throws SQLException, DaoSystemException {

        ConnectionFactoryFactory.setType(ConnectionFactoryFactory.FactoryType.DBCP);
        UserDao dao = new UserDaoImpl();

        List<Long> time = new ArrayList<Long>();

        for (int k = 0; k < COUNT; k++) {
            long tic = System.nanoTime();
            List<User> userList = dao.selectAll();
            long dT = (System.nanoTime() - tic) / 1000;
            System.out.println(dT);
            time.add(dT);
        }

        Collections.sort(time);
        long sum = 0;
        for (int k = COUNT / 10; k < COUNT - COUNT / 10; k++) {
            sum += time.get(k);
        }
        System.out.println("avg = " + 10 * sum / (COUNT * 8));


    }
}
