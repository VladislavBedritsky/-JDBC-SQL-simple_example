package com.htp.ex.service.service;

import com.htp.ex.service.entity.Auto;
import com.htp.ex.service.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reports {

    private void update(Connection connection, String s) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(s);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserNameById (Connection connection) {

        String s = "update example.users set email='ccc#mail.com' where id = 3";
        update(connection,s);
    }

    public void createTableUsers (Connection connection) {

        String s = "create table users (user_id int not null primary key, username varchar(255), email varchar (255), age int)";
        update(connection,s);
    }

    public void createTableAutos (Connection connection) {

        String s = "create table autos (auto_id int not null primary key, model varchar(255), color varchar(255), price int)";
        update(connection,s);
    }

    public void createTableOrders (Connection connection) {

        String s = "create table orders (order_id int not null primary key, user_id int not null references example.users(user_id), auto_id int not null references example.autos(auto_id))";
        update(connection,s);
    }

    public void insertUser (Connection connection) {

        String s = "insert into example.users (user_id, username, email, age) values (4, 'vitaliy', 'vtl@mail.ru', 20)";
        update(connection,s);
    }

    public void insertAuto (Connection connection) {

        String s = "insert into example.autos (auto_id, model, color, price) values (4, 'fiat', 'whit', 3500)";
        update(connection,s);
    }

    public void insertOrder (Connection connection) {

        String s = "insert into example.orders (order_id, user_id, auto_id) values (6,4,1)";
        update(connection,s);
    }

    public void deleteOrder (Connection connection) {

        String s = "delete from example.orders where order_id=6";
        update(connection,s);
    }

    public void changeAutoModelAndColor (Connection connection) {

        String s = "update example.autos set example.autos.model = 'mercedes', example.autos.color = 'blue' where example.autos.auto_id = 3";
        update(connection,s);
    }

    public void displayAllUsers (Connection connection) {

        String s = "select * from example.users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(s);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setAge(resultSet.getInt("age"));

                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayMercedesById (Connection connection) {

        String s = "select  example.autos.auto_id from example.autos where example.autos.model = 'mercedes'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(s);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Auto auto = new Auto();
                auto.setId(resultSet.getInt("auto_id"));

                System.out.println(auto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public void displayUserIdAndUserNameAndAutoModelAndAutoPriceMoreThan3000AndSortedByAutoModel (Connection connection) {

        String s = "SELECT example.users.user_id, example.users.username, example.autos.model, MAX(example.autos.price) "+
                "FROM example.orders JOIN example.users ON example.orders.user_id = example.users.user_id " +
                "JOIN example.autos ON example.orders.auto_id = example.autos.auto_id " +
                "GROUP BY example.users.user_id, example.users.username, example.autos.model " +
                "HAVING MAX(example.autos.price)>3000 "+
                "Order BY MAX(example.autos.price) DESC";

       try {
           PreparedStatement preparedStatement = connection.prepareStatement(s);
           ResultSet resultSet = preparedStatement.executeQuery();

           while (resultSet.next()) {
               System.out.println(resultSet.getInt("user_id")+"_"+resultSet.getString("username")+
                       "_"+resultSet.getString("model")+"_"+resultSet.getInt("MAX(example.autos.price)"));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
}
