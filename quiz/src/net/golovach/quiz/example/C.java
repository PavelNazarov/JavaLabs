package net.golovach.quiz.example;

import com.mysql.jdbc.ConnectionProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: vaha
 * Date: 2/13/13
 * Time: 1:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class C {
    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/quiz_db?profileSQL=true";
    public static final String LOGIN = "username";
    public static final String PASSWORD = "password";

    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection(JDBC_URL, LOGIN, PASSWORD);
        Statement stmt = conn.createStatement();
        stmt.execute("select * from quizes");
        System.err.println("---");
        stmt.execute("select * from quizes");
    }
}
