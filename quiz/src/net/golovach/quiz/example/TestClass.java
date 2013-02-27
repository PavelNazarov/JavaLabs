package net.golovach.quiz.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 26.02.13
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public class TestClass {
    public static void main(String[] args) throws SQLException {
        ClassLoader cl =TestClass.class.getClassLoader();
        Class<?>[] interfaces = {Connection.class};

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                return null;
            }
        };

        Connection connection = (Connection)Proxy.newProxyInstance(cl,interfaces,handler);
        connection.close();
    }
}
