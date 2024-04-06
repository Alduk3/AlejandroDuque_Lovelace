package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    // Connection
    public  static Connection connection = null;

    // Method to connect db
    public static Connection openConnection(){
        try {
            // Call driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection variables
            String url = "jdbc:mysql://localhost:3306/bookstore";
            String user = "root";
            String password = "";

            // Establish connection
            connection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Connection successful");

        } catch (ClassNotFoundException error){
            System.out.println("Error >> driver not installed " + error.getMessage());
        } catch (SQLException error){
            System.out.println("Error >> error connecting db " + error.getMessage());
        }

        return connection;
    }

    // Method to finish connection
    public static void closeConnection(){
        try {
            // If connection is active, close
            if (connection != null){
                connection.close();
                System.out.println("Connection finished successful");
            }
        } catch (SQLException error) {
            System.out.println("Error >> " + error.getMessage());
        }
    }
}
