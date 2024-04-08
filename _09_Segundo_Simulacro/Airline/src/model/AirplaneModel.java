package model;

import database.CRUD;
import database.ConfigDB;
import entity.Airplane;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AirplaneModel implements CRUD {
    @Override
    public Object insert(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to airplane
        Airplane airplane = (Airplane) object;

        try {
            // SQL
            String sql = "INSERT INTO airplane(model, capacity) VALUES (?, ?);";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set value to ?
            preparedStatement.setString(1, airplane.getModel());
            preparedStatement.setInt(2, airplane.getCapacity());

            // Execute query
            preparedStatement.execute();

            // Get response
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                airplane.setId_airplane(resultSet.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Doctor insertion was successful.");

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return airplane;
    }

    @Override
    public List<Object> findAll() {
        // Open Connection
        Connection connection = ConfigDB.openConnection();

        // Create List to airplane
        List<Object> airplaneList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM airplane;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //  Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                // Create new Airplane
                Airplane airplane = new Airplane();

                // Get Info to airplane
                airplane.setId_airplane(resultSet.getInt("id_airplane"));
                airplane.setModel(resultSet.getString("model"));
                airplane.setCapacity(resultSet.getInt("capacity"));

                // Add airplane to list
                airplaneList.add(airplane);
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return airplaneList;
    }

    @Override
    public boolean update(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to airplane
        Airplane airplane = (Airplane) object;

        // isUpdate
        boolean isUpdate = false;

        try {
            // SQL
            String sql = "UPDATE airplane SET model = ?, capacity = ? WHERE id_airplane = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1, airplane.getModel());
            preparedStatement.setInt(2, airplane.getCapacity());
            preparedStatement.setInt(3, airplane.getId_airplane());

            // Execute query
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return isUpdate;
    }

    @Override
    public boolean delete(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to airplane
        Airplane airplane = (Airplane) object;

        // Delete status
        boolean isDeleted = false;

        try {
            // SQL
            String sql = "DELETE FROM airplane WHERE id_airplane = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, airplane.getId_airplane());

            // Execute query
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was successful");
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();;
        }

        return isDeleted;
    }

    public int findCapacity(int id_airplane){
        // Open connection
        Connection  connection = ConfigDB.openConnection();

        // Create capacity
        int airplane_capacity = 0;

        try {
            // SQL
            String sql = "SELECT capacity FROM airplane WHERE id_airplane = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, id_airplane);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                airplane_capacity = resultSet.getInt("capacity");
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return airplane_capacity;
    }
}
