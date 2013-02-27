package net.golovach.quiz.example;

import net.golovach.quiz.dao.UserDao;
import net.golovach.quiz.dao.exception.DaoException;
import net.golovach.quiz.dao.impl.UserDaoImpl;
import net.golovach.quiz.dao.impl.jdbc.ConnectionFactoryFactory;
import net.golovach.quiz.entity.User;

import java.sql.SQLException;

public class SimpleUserExample {

    public static void main(String[] args) throws DaoException, SQLException {
        ConnectionFactoryFactory.setType(ConnectionFactoryFactory.FactoryType.DBCP);

        UserDao dao = new UserDaoImpl();

        System.out.println("ALL CURRENT USERS:");
        for (User user : dao.selectAll()) {
            System.out.println("    " + user.toString());
        }

        System.out.println("DELETE:");
        for (User user : dao.selectAll()) {
            dao.deleteById(user.getId());
            System.out.println("    User with id = " + user.getId() + " deleted");
        }

        System.out.println("INSERT NEW:");
        dao.insert(newUser(1, "Mike1", "x@a.com"));
        System.out.println("    'Mike' inserted");
        dao.insert(newUser(2, "Sara1", "y@b.com"));
        System.out.println("    'Sara' inserted");
        dao.insert(newUser(3, "Anna1", "zcx.com"));
        System.out.println("    'Anna' inserted");

        System.out.println("ALL CURRENT USERS:");
        for (User user : dao.selectAll()) {
            System.out.println("    " + user.toString());
        }

        ConnectionFactoryFactory.close();
    }

    public static User newUser(int id, String login, String email) {
        User result = new User(id);
        result.setLogin(login);
        result.setEmail(email);
        return result;
    }
}

