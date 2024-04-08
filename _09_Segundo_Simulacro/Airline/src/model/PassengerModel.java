package model;

import database.CRUD;
import database.ConfigDB;
import entity.Passenger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PassengerModel implements CRUD {
    @Override
    public Object insert(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to passenger
        Passenger passenger = (Passenger) object;

        try {
            // SQL
            String sql = "INSERT INTO passenger(name, last_name, identification_document) VALUES (?, ?, ?);";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set value to ?
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getLast_name());
            preparedStatement.setString(3, passenger.getIdentification_document());

            // Execute query
            preparedStatement.execute();

            // Get response to set id
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while(resultSet.next()){
                passenger.setId_passenger(resultSet.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Passenger insertion was successful.");

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return passenger;
    }

    @Override
    public List<Object> findAll() {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to passengers
        List<Object> passengerList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM passenger;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create passenger
                Passenger passenger = new Passenger();

                passenger.setId_passenger(resultSet.getInt("id_passenger"));
                passenger.setName(resultSet.getString("name"));
                passenger.setLast_name(resultSet.getString("last_name"));
                passenger.setIdentification_document(resultSet.getString("identification_document"));

                // Add passenger to list
                passengerList.add(passenger);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return passengerList;
    }

    @Override
    public boolean update(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to passenger
        Passenger passenger = (Passenger) object;

        // Update status
        boolean isUpdated = false;

        try {
            // SQL
            String sql = "UPDATE passenger SET name = ?, last_name = ?, identification_document = ? WHERE id_passenger = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set values to ?
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getLast_name());
            preparedStatement.setString(3, passenger.getIdentification_document());
            preparedStatement.setInt(4, passenger.getId_passenger());

            // Execute query
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "The update was successful.");
            }


        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return isUpdated;
    }

    @Override
    public boolean delete(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to passenger
        Passenger passenger = (Passenger) object;

        // Delete status
        boolean isDeleted = false;

        try {
            // SQL
            String sql = "DELETE FROM passenger WHERE id_passenger = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, passenger.getId_passenger());

            // Execute query
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was successful.");
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return isDeleted;
    }

    public List<Passenger> findByName(String name){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to passenger
        List<Passenger> passengersList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM passenger WHERE name LIKE ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1, "%" + name + "%");

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Passenger passenger = new Passenger();

                passenger.setId_passenger(resultSet.getInt("id_passenger"));
                passenger.setName(resultSet.getString("name"));
                passenger.setLast_name(resultSet.getString("last_name"));
                passenger.setIdentification_document(resultSet.getString("identification_document"));

                // Add to list
                passengersList.add(passenger);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return passengersList;
    }
}
