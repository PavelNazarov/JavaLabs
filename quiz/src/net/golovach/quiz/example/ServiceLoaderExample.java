package net.golovach.quiz.example;

import java.io.IOException;
import java.net.URL;
import java.sql.Driver;
import java.util.Enumeration;
import java.util.ServiceLoader;

public class ServiceLoaderExample {

    public static void main(String[] args) throws IOException {
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        for (Driver driver : loader) {
            System.out.println(driver);
        }
    }
}
