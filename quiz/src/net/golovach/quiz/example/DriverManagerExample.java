package net.golovach.quiz.example;

import java.io.IOException;
import java.net.URL;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.ServiceLoader;

public class DriverManagerExample {

    public static void main(String[] args) throws IOException {
        Enumeration<Driver> iterator = DriverManager.getDrivers();
        while (iterator.hasMoreElements()) {
            System.out.println(iterator.nextElement());
        }
    }
}
