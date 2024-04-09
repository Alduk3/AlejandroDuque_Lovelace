package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {
    @Override
    public Object insert(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to coder
        Coder coder = (Coder) object;

        try {
            // SQL
            String sql = "INSERT INTO coder(name, lastname, document, cohort, cv, clan) VALUES (?,?,?,?,?,?);";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // set value to ?
            preparedStatement.setString(1, coder.getName());
            preparedStatement.setString(2, coder.getLastname());
            preparedStatement.setString(3, coder.getDocument());
            preparedStatement.setInt(4, coder.getCohort());
            preparedStatement.setString(5, coder.getCv());
            preparedStatement.setString(6, coder.getClan());

            // Execute query
            preparedStatement.execute();

            // Get id_coder
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                coder.setId_coder(resultSet.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Coder insertion was successful.");
        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return coder;
    }

    @Override
    public List<Object> findAll() {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to coders
        List<Object> coderList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM coder;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create new coder
                Coder coder = new Coder();

                // Set values to coder
                coder.setId_coder(resultSet.getInt("id_coder"));
                coder.setName(resultSet.getString("name"));
                coder.setLastname(resultSet.getString("lastname"));
                coder.setDocument(resultSet.getString("document"));
                coder.setCohort(resultSet.getInt("cohort"));
                coder.setCv(resultSet.getString("cv"));
                coder.setClan(resultSet.getString("clan"));

                // Add coder to list
                coderList.add(coder);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return coderList;
    }

    @Override
    public boolean update(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to coder
        Coder coder = (Coder) object;

        // Update status
        boolean isUpdated = false;

        try {
            // SQL
            String sql = "UPDATE coder SET name = ?, lastname = ?, document = ?, cohort = ?, cv = ?, clan= ? WHERE id_coder = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1, coder.getName());
            preparedStatement.setString(2, coder.getLastname());
            preparedStatement.setString(3, coder.getDocument());
            preparedStatement.setInt(4, coder.getCohort());
            preparedStatement.setString(5, coder.getCv());
            preparedStatement.setString(6, coder.getClan());
            preparedStatement.setInt(7, coder.getId_coder());

            // Execute query and get response
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "The update was successful.");
            }

        } catch (Exception error) {
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

        // Convert object to coder
        Coder coder = (Coder) object;

        // Delete status
        boolean isDeleted = false;

        try {
            // SQL
            String sql = "DELETE FROM coder WHERE id_coder = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, coder.getId_coder());

            // Execute query and get response
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

    public List<Coder> findByTechnology(String technology){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to coders
        List<Coder> coderList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM coder WHERE cv LIKE ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1, "%" + technology + "%");

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create new coder
                Coder coder = new Coder();

                // Set values to coder
                coder.setId_coder(resultSet.getInt("id_coder"));
                coder.setName(resultSet.getString("name"));
                coder.setLastname(resultSet.getString("lastname"));
                coder.setDocument(resultSet.getString("document"));
                coder.setCohort(resultSet.getInt("cohort"));
                coder.setCv(resultSet.getString("cv"));
                coder.setClan(resultSet.getString("clan"));

                // Add coder to list
                coderList.add(coder);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return coderList;
    }

    public List<Coder> findByClan(String clan){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to coders
        List<Coder> coderList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM coder WHERE clan LIKE ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1, "%" + clan + "%");

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create new coder
                Coder coder = new Coder();

                // Set values to coder
                coder.setId_coder(resultSet.getInt("id_coder"));
                coder.setName(resultSet.getString("name"));
                coder.setLastname(resultSet.getString("lastname"));
                coder.setDocument(resultSet.getString("document"));
                coder.setCohort(resultSet.getInt("cohort"));
                coder.setCv(resultSet.getString("cv"));
                coder.setClan(resultSet.getString("clan"));

                // Add coder to list
                coderList.add(coder);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return coderList;
    }
    public List<Coder> findByCohort(int cohort){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to coders
        List<Coder> coderList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM coder WHERE cohort LIKE ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, cohort);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create new coder
                Coder coder = new Coder();

                // Set values to coder
                coder.setId_coder(resultSet.getInt("id_coder"));
                coder.setName(resultSet.getString("name"));
                coder.setLastname(resultSet.getString("lastname"));
                coder.setDocument(resultSet.getString("document"));
                coder.setCohort(resultSet.getInt("cohort"));
                coder.setCv(resultSet.getString("cv"));
                coder.setClan(resultSet.getString("clan"));

                // Add coder to list
                coderList.add(coder);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return coderList;
    }


}
