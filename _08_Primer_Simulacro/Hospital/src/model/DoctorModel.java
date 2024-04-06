package model;

import database.CRUD;
import database.ConfigDB;
import entity.Doctor;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorModel implements CRUD {
    @Override
    public Object insert(Object object) {
        // open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to Doctor
        Doctor doctor = (Doctor) object;

        try {
            // SQL
            String sql = "INSERT INTO doctor(name, last_name, id_specialty) VALUES (?, ?, ?);";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set values to ?
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getLast_name());
            preparedStatement.setInt(3, doctor.getId_specialty());

            // Execute
            preparedStatement.execute();

            // Get response
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                doctor.setId_doctor(resultSet.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Doctor insertion was  successful.");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        }
        // Close connection
        ConfigDB.closeConnection();

        return doctor;
    }

    @Override
    public List<Object> findAll() {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // List
        List<Object> listDoctors = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM doctor";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Query result
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Doctor doctor = new Doctor();

                doctor.setId_doctor(resultSet.getInt("id_doctor"));
                doctor.setName(resultSet.getString("name"));
                doctor.setLast_name(resultSet.getString("last_name"));
                doctor.setId_specialty(resultSet.getInt("id_specialty"));

                listDoctors.add(doctor);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        }

        // close connection
        ConfigDB.closeConnection();

        return listDoctors;
    }

    @Override
    public boolean update(Object object) {
        // open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to Doctor
        Doctor doctor = (Doctor) object;

        // Status
        boolean isUpdate = false;

        try {
            // SQL
            String sql = "UPDATE doctor SET name = ?, last_name = ?,id_specialty = ? WHERE id_doctor = ?;";

            //Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getLast_name());
            preparedStatement.setInt(3, doctor.getId_specialty());
            preparedStatement.setInt(4, doctor.getId_doctor());

            // Query Execute
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0) {
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

        // Convert object to Doctor
        Doctor doctor = (Doctor) object;

        // status
        boolean isDelete = false;

        try {
            // SQL query
            String sql = "DELETE FROM doctor WHERE id_doctor = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, doctor.getId_doctor());

            // Query execute
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"Delete was successful");
            }

        } catch ( Exception error ) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            // close connection
             ConfigDB.closeConnection();
        }

        return isDelete;
    }

    public List<Doctor> findBySpecialty(int id_specialty){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to doctors
        List<Doctor> doctorList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM doctor WHERE id_specialty = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, id_specialty);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Doctor doctor = new Doctor();

                doctor.setId_doctor(resultSet.getInt("id_doctor"));
                doctor.setName(resultSet.getString("name"));
                doctor.setLast_name(resultSet.getString("last_name"));
                doctor.setId_specialty(resultSet.getInt("id_specialty"));

                doctorList.add(doctor);
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return doctorList;
    }
}
