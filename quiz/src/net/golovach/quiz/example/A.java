package net.golovach.quiz.example;

import com.mysql.jdbc.SQLError;

/**
 * Created by IntelliJ IDEA.
 * User: vaha
 * Date: 2/13/13
 * Time: 1:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class A {
    public static void main(String[] args) throws Exception {
        SQLError.dumpSqlStatesMappingsAsXml();
    }
}
