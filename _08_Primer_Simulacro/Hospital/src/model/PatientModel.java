package model;

import database.CRUD;
import database.ConfigDB;
import entity.Patient;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PatientModel implements CRUD {
    @Override
    public Object insert(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to Patient
        Patient patient = (Patient) object;

        try {
            // SQL query
            String sql = "INSERT INTO patient(name, last_name, date_birth, identification_document) VALUES (?,?,?,?);";

            // Prepare Statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // set values to ?
            preparedStatement.setString(1,patient.getName());
            preparedStatement.setString(2,patient.getLast_name());
            preparedStatement.setString(3,patient.getDate_birth());
            preparedStatement.setString(4,patient.getIdentification_document());

            // Query execute
            preparedStatement.execute();

            // Get response
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                patient.setId_patient(resultSet.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Patient insertion was  successful.");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        }

        // close connection
        ConfigDB.closeConnection();

        return patient;
    }

    @Override
    public List<Object> findAll() {

        // Create list to save the response
        List<Object> listPatients = new ArrayList<>();

        // Open connection
        Connection connection = ConfigDB.openConnection();

        try {
            // SQL query
            String sql = "SELECT * FROM patient;";

            // Prepare Statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Query Result
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Patient patient = new Patient();

                patient.setId_patient(resultSet.getInt("id_patient"));
                patient.setName(resultSet.getString("name"));
                patient.setLast_name(resultSet.getString("last_name"));
                patient.setDate_birth(resultSet.getString("date_birth"));
                patient.setIdentification_document(resultSet.getString("identification_document"));

                listPatients.add(patient);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        }

        ConfigDB.closeConnection();

        return listPatients;
    }

    @Override
    public boolean update(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to Patient
        Patient patient = (Patient) object;

        // Status
        boolean isUpdate = false;

        try {
            // SQL
            String sql = "UPDATE patient SET name = ?, last_name = ?, date_birth = ?, identification_document = ? WHERE id_patient = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getLast_name());
            preparedStatement.setString(3, patient.getDate_birth());
            preparedStatement.setString(4, patient.getIdentification_document());
            preparedStatement.setInt(5, patient.getId_patient());

            // Query execute
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
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
        // open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to Patient
        Patient patient = (Patient) object;

        // status delete
        boolean isDeleted = false;

        try {
            // SQL query
            String sql = "DELETE FROM patient WHERE id_patient = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, patient.getId_patient());

            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Delete was successful");
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        }
        // Close connection
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public Patient findByIdentification_document(String document){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create patient
        Patient patient = null;

        try {
            // SQL
            String sql = "SELECT * FROM patient WHERE identification_document = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1, document );

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                // Create patient
                patient = new Patient();

                patient.setId_patient(resultSet.getInt("id_patient"));
                patient.setName(resultSet.getString("name"));
                patient.setLast_name(resultSet.getString("last_name"));
                patient.setDate_birth(resultSet.getString("date_birth"));
                patient.setIdentification_document(resultSet.getString("identification_document"));

            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return patient;
    }
}
