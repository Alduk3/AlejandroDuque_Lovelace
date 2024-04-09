package model;

import database.CRUD;
import database.ConfigDB;
import entity.Hiring;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HiringModel implements CRUD {
    @Override
    public Object insert(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to hiring
        Hiring hiring = (Hiring) object;

        try {
            // SQL
            String sql = "INSERT INTO hiring(id_vacant, id_coder, status, salary) VALUES (?, ?, ?, ?);";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set value to ?
            preparedStatement.setInt(1, hiring.getId_vacant());
            preparedStatement.setInt(2, hiring.getId_coder());
            if (hiring.isStatus()){
                preparedStatement.setString(3, "active");
            } else {
                preparedStatement.setString(3, "inactive");
            }
            preparedStatement.setDouble(4, hiring.getSalary());

            // Execute query
            preparedStatement.execute();

            // Get response
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while(resultSet.next()){
                hiring.setId_hiring(resultSet.getInt(1));
            }

            sql = "SELECT * FROM hiring WHERE id_hiring = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, hiring.getId_hiring());

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                hiring.setApplication_date(resultSet.getString("application_date"));
            }

            JOptionPane.showMessageDialog(null, "The insertion was successful.");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return hiring;
    }

    @Override
    public List<Object> findAll() {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to hiring
        List<Object> hiringList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM hiring;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create new hiring
                Hiring hiring = new Hiring();

                hiring.setId_hiring(resultSet.getInt("id_hiring"));
                hiring.setId_vacant(resultSet.getInt("id_vacant"));
                hiring.setId_coder(resultSet.getInt("id_coder"));
                hiring.setApplication_date(resultSet.getString("application_date"));
                hiring.setStatus(resultSet.getString("status").equals("active"));
                hiring.setSalary(resultSet.getDouble("salary"));

                // Add hiring to list
                hiringList.add(hiring);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return hiringList;
    }

    @Override
    public boolean update(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to hiring
        Hiring hiring = (Hiring) object;

        // Update status
        boolean isUpdate = false;

        try {
            // SQL
            String sql = "UPDATE hiring SET id_vacant = ?, id_coder = ?, status = ?, salary = ? WHERE id_hiring = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, hiring.getId_vacant());
            preparedStatement.setInt(2, hiring.getId_coder());
            if (hiring.isStatus()){
                preparedStatement.setString(3, "active");
            } else {
                preparedStatement.setString(3, "inactive");
            }
            preparedStatement.setDouble(4, hiring.getSalary());
            preparedStatement.setInt(5, hiring.getId_hiring());


            // Execute query and get row affected
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The delete was successful.");
            }
        } catch (Exception error) {
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

        // Convert object to hiring
        Hiring hiring = (Hiring) object;

        // Delete status
        boolean isDeleted = false;

        try {
            // SQL
            String sql = "DELETE FROM hiring WHERE id_hiring = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, hiring.getId_hiring());

            // Execute query and get row affected
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
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
}
