package model;

import database.CRUD;
import database.ConfigDB;
import entity.Specialty;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyModel implements CRUD {

    @Override
    public Object insert(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert Object to Specialty
        Specialty specialty = (Specialty) object;

        try {
            // SQL query
            String sql = "INSERT INTO specialty (name, description) VALUES (?,?);";

            // prepare Statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // set values to ?
            preparedStatement.setString(1,specialty.getName());
            preparedStatement.setString(2,specialty.getDescription());

            // Query execute
            preparedStatement.execute();

            // get response
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            // loop response
            while (resultSet.next()){
                specialty.setId_specialty(resultSet.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Specialty insertion was  successful.");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return specialty;
    }

    @Override
    public List<Object> findAll() {

        // Create list to save the response
        List<Object> listSpecialties = new ArrayList<>();

        // Open connection
        Connection connection = ConfigDB.openConnection();

        try {
            // SQL query
            String sql = "SELECT * FROM specialty;";

            // Use prepareStatement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Query execute
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Specialty specialty = new Specialty();

                specialty.setId_specialty(resultSet.getInt("id_specialty"));
                specialty.setName(resultSet.getString("name"));
                specialty.setDescription(resultSet.getString("description"));

                listSpecialties.add(specialty);
            }


        } catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();

        return listSpecialties;
    }

    @Override
    public boolean update(Object object) {
        // open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to specialty
        Specialty specialty = (Specialty) object;

        // Status action
        boolean isUpdate = false;

        try {
            // SQL query
            String sql = "UPDATE specialty SET name = ?, description = ? WHERE id_specialty = ?;";

            // Prepare Statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // set values to Query
            preparedStatement.setString(1, specialty.getName());
            preparedStatement.setString(2, specialty.getDescription());
            preparedStatement.setInt(3, specialty.getId_specialty());

            // Query execute
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }


        return isUpdate;
    }

    @Override
    public boolean delete(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to specialty
        Specialty specialty = (Specialty) object;

        // Status
        boolean isDeleted = false;

        try {
            // SQL query
            String sql = "DELETE FROM specialty WHERE id_specialty = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, specialty.getId_specialty());

            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Delete was successful");
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR >> " + error.getMessage());
        }
        // Close connection
        ConfigDB.closeConnection();

        return isDeleted;
    }
}
