package com.yc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @author: 作者
 * @create: 2021-03-30 19:29
 */
@DBConnection(url = "jdbc:mysql://localhost:3306/test", driverClass = "com.mysql.jdbc.Driver")
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class c = Test.class;

        //Annotation[] ans=c.getDeclaredAnnotations();
        DBConnection dbc = (DBConnection) c.getDeclaredAnnotation(DBConnection.class);
        String driverClass = dbc.driverClass();
        String user = dbc.user();
        String url = dbc.url();
        String password = dbc.password();

        Class.forName(driverClass);
        Connection con = DriverManager.getConnection(url, user, password);
        System.out.println(con);
    }

}
