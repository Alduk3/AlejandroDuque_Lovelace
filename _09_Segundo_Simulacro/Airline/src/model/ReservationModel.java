package model;

import database.CRUD;
import database.ConfigDB;
import entity.Reservation;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservationModel implements CRUD {
    @Override
    public Object insert(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to Reservation
        Reservation reservation = (Reservation) object;

        try {
            // SQL
            String sql = "INSERT INTO reservation(id_passenger, id_flight, reservation_date, seat) VALUES (?,?,?,?);";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set value to ?
            preparedStatement.setInt(1, reservation.getId_passenger());
            preparedStatement.setInt(2, reservation.getId_flight());
            preparedStatement.setString(3, reservation.getReservation_date());
            preparedStatement.setString(4, reservation.getSeat());

            // Execute query
            preparedStatement.execute();

            // Get response
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                reservation.setId_reservation(resultSet.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Reservation was successful.");

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return reservation;
    }

    @Override
    public List<Object> findAll() {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to Reservations
        List<Object> reservationList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM reservation;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create new Reservation
                Reservation reservation = new Reservation();

                // set data to reservation
                reservation.setId_reservation(resultSet.getInt("id_reservation"));
                reservation.setId_passenger(resultSet.getInt("id_passenger"));
                reservation.setId_flight(resultSet.getInt("id_flight"));
                reservation.setReservation_date(resultSet.getString("reservation_date"));
                reservation.setSeat(resultSet.getString("seat"));

                // Add reservation to list
                reservationList.add(reservation);
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return reservationList;
    }

    @Override
    public boolean update(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to reservation
        Reservation reservation = (Reservation) object;

        // Update status
        boolean isUpdated = false;

        try {
            // SQL
            String sql = "UPDATE reservation SET id_passenger = ?, id_flight = ?, reservation_date = ?, seat = ? WHERE id_reservation = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, reservation.getId_passenger());
            preparedStatement.setInt(2, reservation.getId_flight());
            preparedStatement.setString(3, reservation.getReservation_date());
            preparedStatement.setString(4, reservation.getSeat());
            preparedStatement.setInt(5, reservation.getId_reservation());

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

        // Convert object to reservation
        Reservation reservation = (Reservation) object;

        // Delete status
        boolean isDeleted = false;

        try {
            // SQL
            String sql = "DELETE FROM reservation WHERE id_reservation = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, reservation.getId_reservation());

            // Execute query
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was successful.");
            }
        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return isDeleted;
    }

    public List<Reservation> findByFlight(int id_flight){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to reservations
        List<Reservation> reservationsList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM reservation WHERE id_flight = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, id_flight);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Reservation reservation = new Reservation();

                reservation.setId_passenger(resultSet.getInt("id_passenger"));
                reservation.setId_flight(resultSet.getInt("id_flight"));
                reservation.setReservation_date(resultSet.getString("reservation_date"));
                reservation.setSeat(resultSet.getString("seat"));

                // Add to array
                reservationsList.add(reservation);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return reservationsList;
    }

    public int findNumberBookedFlights(int id_flight){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        int numberBookedFlights = 0;

        try {
            // SQL
            String sql = "SELECT COUNT(*) from reservation where id_flight = ?;";

            // Prepare Statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, id_flight);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                numberBookedFlights = resultSet.getInt(1);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return numberBookedFlights;
    }

    public int findNumberSimilarSeat(int id_flight, String seat){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        int numberSimilarSeat = 0;

        try {
            // SQL
            String sql = "SELECT COUNT(*) from reservation where id_flight = ? AND seat = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, id_flight);
            preparedStatement.setString(2, seat);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                numberSimilarSeat = resultSet.getInt(1);
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
         ConfigDB.closeConnection();
        }

        return numberSimilarSeat;
    }
}
