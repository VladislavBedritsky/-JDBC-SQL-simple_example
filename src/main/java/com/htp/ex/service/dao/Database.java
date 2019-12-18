package com.htp.ex.service.dao;

import com.htp.ex.service.service.Reports;

import java.sql.*;

public class Database {

    private Reports reports = new Reports();
    private String dbHost = "localhost";
    private String dbPort = "3306";
    private String dbUser = "root1";
    private String dbPass = "123";
    private String dbName = "example";
    private String connectionString = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;;

    public void connection () {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(connectionString,dbUser,dbPass);
            if(!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");

                reports.displayMercedes(connection);             //_________________

                connection.close();
            }

            connection.close();

            if (connection.isClosed()) {
                System.out.println("Соединение с БД закрыто");
            }

        }catch (ClassNotFoundException e) {
            System.out.println("Driver is not found");
        }catch (SQLException e) {
            System.out.println("Bad connection");
        }
    }

}
