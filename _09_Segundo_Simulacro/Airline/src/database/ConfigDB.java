package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    public static Connection connection = null;

    // Connect to database

    public  static  Connection openConnection(){
        try {
            // Call driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection vars

            String url = "jdbc:mysql://localhost:3306/airline";
            String user = "root";
            String password = "";

            // Set connection

            connection = (Connection) DriverManager.getConnection(url,user,password);

            System.out.println("Connection successful :):");

        } catch (ClassNotFoundException error){
            System.out.println("ERROR >> Driver not installed " + error.getMessage());
        } catch (SQLException error){
            System.out.println("ERROR >> error to connect database " + error.getMessage());
        }

        return connection;
    }

    // close connection

    public static void closeConnection(){
        try {
            // if we are connected close
            if (connection != null){
                connection.close();
                System.out.println("Close connection was successful :):");
            }
        } catch (SQLException error){
            System.out.println("ERROR: " + error.getMessage());
        }
    }
}
