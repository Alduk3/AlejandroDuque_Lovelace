package model;

import database.CRUD;
import database.ConfigDB;
import entity.Appointment;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentModel implements CRUD {
    @Override
    public Object insert(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to Appointment
        Appointment appointment = (Appointment) object;

        try {
            // SQL query
            String sql = "INSERT INTO appointment(id_patient, id_doctor, appointment_date, appointment_time, reason) VALUES (?,?,?,?,?);";

            // Prepare Statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // set values to ?
            preparedStatement.setInt(1, appointment.getId_patient());
            preparedStatement.setInt(2, appointment.getId_doctor());
            preparedStatement.setString(3, appointment.getAppointment_date());
            preparedStatement.setString(4, appointment.getAppointment_time());
            preparedStatement.setString(5, appointment.getReason());

            // Query execute
            preparedStatement.execute();

            // Get response
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                appointment.setId_appointment(resultSet.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Appointment insertion was  successful.");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        }

        // close connection
        ConfigDB.closeConnection();

        return appointment;
    }

    @Override
    public List<Object> findAll() {
        // Create list to save appointments
        List<Object> listAppointments = new ArrayList<>();

        // Open connection
        Connection connection = ConfigDB.openConnection();

        try {
            // SQL
            String sql = "SELECT * FROM appointment";

            // Prepare Statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //  Get Response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Appointment appointment = new Appointment();

                appointment.setId_appointment(resultSet.getInt("id_appointment"));
                appointment.setId_patient(resultSet.getInt("id_patient"));
                appointment.setId_doctor(resultSet.getInt("id_doctor"));
                appointment.setAppointment_date(resultSet.getString("appointment_date"));
                appointment.setAppointment_time(resultSet.getString("appointment_time"));
                appointment.setReason(resultSet.getString("reason"));

                listAppointments.add(appointment);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return listAppointments;
    }

    @Override
    public boolean update(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert Object to Appointment
        Appointment appointment = (Appointment) object;

        // Status
        boolean isUpdate = false;

        try{
            // SQL
            String sql = "UPDATE appointment SET id_patient = ?, id_doctor = ?, appointment_date = ?, appointment_time = ?, reason = ? WHERE id_appointment = ?;";

            // Prepare Statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, appointment.getId_patient());
            preparedStatement.setInt(2, appointment.getId_doctor());
            preparedStatement.setString(3, appointment.getAppointment_date());
            preparedStatement.setString(4, appointment.getAppointment_time());
            preparedStatement.setString(5, appointment.getReason());
            preparedStatement.setInt(6, appointment.getId_appointment());

            // Execute
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "The update was successful");
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            // Close connection
            ConfigDB.closeConnection();
        }

        return isUpdate;
    }

    @Override
    public boolean delete(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to appointment
        Appointment appointment = (Appointment) object;

        // Delete status
        boolean isDeleted = false;

        try{
            // SQL
            String sql = "DELETE FROM appointment WHERE id_appointment = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, appointment.getId_appointment());

            // Execute query
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Delete was successful");
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return isDeleted;
    }

    public List<Appointment> findByDate(String appointment_date){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to appointments
        List<Appointment> appointmentList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM appointment WHERE appointment_date = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1, appointment_date);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create appointment
                Appointment appointment = new Appointment();

                // Set appointment data
                appointment.setId_appointment(resultSet.getInt("id_appointment"));
                appointment.setId_patient(resultSet.getInt("id_patient"));
                appointment.setId_doctor(resultSet.getInt("id_patient"));
                appointment.setAppointment_date(resultSet.getString("appointment_date"));
                appointment.setAppointment_time(resultSet.getString("appointment_time"));
                appointment.setReason(resultSet.getString("reason"));

                // add to list
                appointmentList.add(appointment);
            }

        }catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return appointmentList;
    }
}
