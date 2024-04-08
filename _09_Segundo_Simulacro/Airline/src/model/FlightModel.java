package model;

import database.CRUD;
import database.ConfigDB;
import entity.Flight;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlightModel implements CRUD {
    @Override
    public Object insert(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to flight
        Flight flight = (Flight) object;

        try {
            // SQL
            String sql = "INSERT INTO flight(destiny, departure_date, departure_time, id_airplane) VALUES (?, ?, ?, ?);";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set value to ?
            preparedStatement.setString(1, flight.getDestiny());
            preparedStatement.setString(2, flight.getDeparture_date());
            preparedStatement.setString(3, flight.getDeparture_time());
            preparedStatement.setInt(4, flight.getId_airplane());

            // Execute query
            preparedStatement.execute();

            // Get response
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                flight.setId_flight(resultSet.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Flight insertion was successful ");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return flight;
    }

    @Override
    public List<Object> findAll() {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to flight
        List<Object> flightList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM flight;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create new flight
                Flight flight = new Flight();

                // Set data
                flight.setId_flight(resultSet.getInt("id_flight"));
                flight.setDestiny(resultSet.getString("destiny"));
                flight.setDeparture_date(resultSet.getString("departure_date"));
                flight.setDeparture_time(resultSet.getString("departure_time"));
                flight.setId_airplane(resultSet.getInt("id_airplane"));

                // Add flight to list
                flightList.add(flight);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return flightList;
    }

    @Override
    public boolean update(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to flight
        Flight flight = (Flight) object;

        // Update status
        boolean isUpdated = false;

        try {
            // SQL
            String sql = "UPDATE flight SET destiny = ?, departure_date = ?, departure_time = ?, id_airplane = ? WHERE id_flight = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1, flight.getDestiny());
            preparedStatement.setString(2, flight.getDeparture_date());
            preparedStatement.setString(3, flight.getDeparture_time());
            preparedStatement.setInt(4, flight.getId_airplane());
            preparedStatement.setInt(5, flight.getId_flight());

            // Execute query
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "The update was successful.");
            }

        }catch (Exception error){
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

        // Convert object to flight
        Flight flight = (Flight) object;

        // Status delete
        boolean isDeleted = false;

        try {
            // SQL
            String sql = "DELETE FROM flight WHERE id_flight = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, flight.getId_flight());

            // Execute query
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0 ) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The delete was successful. ");
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return isDeleted;
    }

    public List<Flight> findByDestiny(String destiny){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to flights
        List<Flight> flightsList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM flight WHERE destiny = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1, destiny);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Flight flight = new Flight();

                flight.setId_flight(resultSet.getInt("id_flight"));
                flight.setDestiny(resultSet.getString("destiny"));
                flight.setDeparture_date(resultSet.getString("departure_date"));
                flight.setDeparture_time(resultSet.getString("departure_time"));
                flight.setId_airplane(resultSet.getInt("id_airplane"));

                // Add to list
                flightsList.add(flight);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return flightsList;
    }
}
