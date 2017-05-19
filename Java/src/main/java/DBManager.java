package com.company;

import java.sql.*;


/**
 * Created by toma on 18/05/2017.
 */
public class DBManager {

    private static void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            Integer id = resultSet.getInt("id");
            System.out.println("User: " + username);
            System.out.println("Password: " + password);
            System.out.println("Id: " + id);
        }
    }

    public static void main(String[] args) {
        try {
            Connection connect = null;
            Statement statement = null;

            connect = DriverManager.getConnection("jdbc:mysql://mysql7655-catalogpao.molddata.cloud/Catalog", "toma", "parolatoma123");
//
//
//            // This will load the MySQL driver, each DB has its own driver
//            Class.forName("com.mysql.jdbc.Driver");
//            // Setup the connection with the DB
//            connect = DriverManager
//                    .getConnection("jdbc:mysql://cst-dev.com:3306/pao?"
//                            + "user=pao&password=pao");
//
//            statement = connect.createStatement();
////            statement.executeUpdate(
////                            "create table toma(" +
////                            "id int NOT NULL, " +
////                            "username varchar(12) NOT NULL, " +
////                            "password varchar(12) NOT NULL, " +
////                                    "primary key(id))");
//
////            statement = connect.createStatement();
////            statement.executeUpdate("insert into toma " +
////                    "values(1, 'toma', 'parola1')");
////            statement.executeUpdate("insert into toma " +
////                    "values(2, 'radu', 'parola2')");
////            statement.executeUpdate("insert into toma " +
////                    "values(3, 'petrescu', 'parola3')");
//
//            ResultSet resultSet = statement.executeQuery("select * from toma");
//            writeResultSet(resultSet);
        }
        catch(Exception e) {
            System.out.println("Eroare");
            e.printStackTrace();
        }
    }
}
