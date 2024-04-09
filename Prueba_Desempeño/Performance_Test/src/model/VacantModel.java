package model;

import database.CRUD;
import database.ConfigDB;
import entity.Vacant;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VacantModel implements CRUD {
    @Override
    public Object insert(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to vacant
        Vacant vacant = (Vacant) object;

        try {
            // SQL
            String sql = "INSERT INTO vacant(id_company, title, description, duration, status, technology) VALUES(?,?,?,?,?,?);";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set value to ?
            preparedStatement.setInt(1, vacant.getId_company());
            preparedStatement.setString(2, vacant.getTitle());
            preparedStatement.setString(3, vacant.getDescription());
            preparedStatement.setString(4, vacant.getDuration());

            if (vacant.isStatus()){
                preparedStatement.setString(5, "active");
            } else {
                preparedStatement.setString(5, "inactive");
            }
            preparedStatement.setString(6, vacant.getTechnology());

            // Execute query
            preparedStatement.execute();

            // Get response
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                vacant.setId_vacant(resultSet.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "The insertion was successful.");
        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return vacant;
    }

    @Override
    public List<Object> findAll() {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to Vacant
        List<Object> vacantList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM vacant;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Execute and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create vacant
                Vacant vacant = new Vacant();

                vacant.setId_vacant(resultSet.getInt("id_vacant"));
                vacant.setId_company(resultSet.getInt("id_company"));
                vacant.setTitle(resultSet.getString("title"));
                vacant.setDescription(resultSet.getString("description"));
                vacant.setDuration(resultSet.getString("duration"));
                vacant.setStatus(resultSet.getString("status").equals("active"));
                vacant.setTechnology(resultSet.getString("technology"));

                // Add vacant to list
                vacantList.add(vacant);
            }
        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return vacantList;
    }

    @Override
    public boolean update(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to vacant
        Vacant vacant = (Vacant) object;

        // Update status
        boolean isUpdated = false;

        try {
            // SQL
            String sql = "UPDATE vacant SET id_company = ?, title = ?, description = ?, duration = ?, status = ?, technology = ? WHERE id_vacant = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, vacant.getId_company());
            preparedStatement.setString(2, vacant.getTitle());
            preparedStatement.setString(3, vacant.getDescription());
            preparedStatement.setString(4, vacant.getDuration());
            if (vacant.isStatus()){
                preparedStatement.setString(5, "active");
            } else {
                preparedStatement.setString(5, "inactive");
            }
            preparedStatement.setString(6, vacant.getTechnology());
            preparedStatement.setInt(7, vacant.getId_vacant());

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

        // Convert object to vacant
        Vacant vacant = (Vacant) object;

        // Status
        boolean isDeleted = false;

        try {
            // SQL
            String sql = "DELETE FROM vacant WHERE id_vacant = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, vacant.getId_vacant());

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

    public List<Vacant> findByStatus(boolean status){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to vacant
        List<Vacant> vacantList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM vacant WHERE status = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            if (status){
                preparedStatement.setString(1, "active");
            } else {
                preparedStatement.setString(1, "inactive");
            }

            //Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create vacant
                Vacant vacant = new Vacant();

                vacant.setId_vacant(resultSet.getInt("id_vacant"));
                vacant.setId_company(resultSet.getInt("id_company"));
                vacant.setTitle(resultSet.getString("title"));
                vacant.setDescription(resultSet.getString("description"));
                vacant.setDuration(resultSet.getString("duration"));
                vacant.setStatus(resultSet.getString("status").equals("active"));
                vacant.setTechnology(resultSet.getString("technology"));

                // Add vacant to list
                vacantList.add(vacant);
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return vacantList;
    }

    public List<Vacant> findByTechnology(String technology){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to vacant
        List<Vacant> vacantList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM vacant WHERE technology LIKE ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1, "%" + technology + "%");

            //Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create vacant
                Vacant vacant = new Vacant();

                vacant.setId_vacant(resultSet.getInt("id_vacant"));
                vacant.setId_company(resultSet.getInt("id_company"));
                vacant.setTitle(resultSet.getString("title"));
                vacant.setDescription(resultSet.getString("description"));
                vacant.setDuration(resultSet.getString("duration"));
                vacant.setStatus(resultSet.getString("status").equals("active"));
                vacant.setTechnology(resultSet.getString("technology"));

                // Add vacant to list
                vacantList.add(vacant);
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return vacantList;
    }

    public List<Vacant> findByTitle(String title){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to vacant
        List<Vacant> vacantList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM vacant WHERE title  LIKE ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1, "%" + title + "%");

            //Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create vacant
                Vacant vacant = new Vacant();

                vacant.setId_vacant(resultSet.getInt("id_vacant"));
                vacant.setId_company(resultSet.getInt("id_company"));
                vacant.setTitle(resultSet.getString("title"));
                vacant.setDescription(resultSet.getString("description"));
                vacant.setDuration(resultSet.getString("duration"));
                vacant.setStatus(resultSet.getString("status").equals("active"));
                vacant.setTechnology(resultSet.getString("technology"));

                // Add vacant to list
                vacantList.add(vacant);
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return vacantList;
    }

    public Vacant findById(int id_vacant){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // create vacant
        Vacant vacant = new Vacant();

        try {
            // SQL
            String sql = "SELECT * FROM vacant WHERE id_vacant = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // set value to ?
            preparedStatement.setInt(1, id_vacant);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                vacant.setId_vacant(resultSet.getInt("id_vacant"));
                vacant.setId_company(resultSet.getInt("id_company"));
                vacant.setTitle(resultSet.getString("title"));
                vacant.setDescription(resultSet.getString("description"));
                vacant.setDuration(resultSet.getString("duration"));
                vacant.setStatus(resultSet.getString("status").equals("active"));
                vacant.setTechnology(resultSet.getString("technology"));
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return vacant;
    }

    public boolean updateStatus(Vacant vacant){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Update status
        boolean isUpdated = false;

        try {
            // SQL
            String sql = "UPDATE vacant SET status = ? WHERE id_vacant = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            if (vacant.isStatus()){
                preparedStatement.setString(1, "inactive");
            } else {
                preparedStatement.setString(1, "active");
            }
            preparedStatement.setInt(2, vacant.getId_vacant());

            // Execute query and get response
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "The update status was successful.");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return  isUpdated;
    }
}
