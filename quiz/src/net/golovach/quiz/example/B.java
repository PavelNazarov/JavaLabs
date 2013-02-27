package net.golovach.quiz.example;

import com.mysql.jdbc.ConnectionProperties;
import com.mysql.jdbc.SQLError;
import net.golovach.quiz.dao.AbstractDao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by IntelliJ IDEA.
 * User: vaha
 * Date: 2/13/13
 * Time: 1:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class B {
    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/quiz_db";
    public static final String LOGIN = "username";
    public static final String PASSWORD = "password";

    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection(JDBC_URL, LOGIN, PASSWORD);
        ConnectionProperties p = (ConnectionProperties) conn;
        System.out.println(p.exposeAsXml());
    }
}
