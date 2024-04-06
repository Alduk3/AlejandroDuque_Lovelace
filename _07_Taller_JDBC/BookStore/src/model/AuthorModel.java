package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AuthorModel implements CRUD {
    @Override
    public Object insert(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to Author
        Author author = (Author) object;

        try{
            // SQL
            String sql = "INSERT INTO author(name, nationality) VALUES (?,?);";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set values to ?
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getNationality());

            // Execute query
            preparedStatement.execute();

            // Get Result
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while(resultSet.next()){
                author.setId_author(resultSet.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Author insertion was  successful.");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return author;
    }

    @Override
    public List<Object> findAll() {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list
        List<Object> listAuthors = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM author;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Execute query and get Response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create author
                Author author = new Author();

                // Set info
                author.setId_author(resultSet.getInt("id_author"));
                author.setName(resultSet.getString("name"));
                author.setNationality(resultSet.getString("nationality"));

                // Add author to list
                listAuthors.add(author);
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return listAuthors;
    }

    @Override
    public boolean update(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to author
        Author author = (Author) object;

        // Update status
        boolean isUpdated = false;

        try {
            // SQL
            String sql = "UPDATE author SET name = ?, nationality = ? WHERE id_author = ?;";

            // Prepare Statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getNationality());
            preparedStatement.setInt(3, author.getId_author());

            // Execute Query
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

        // Convert object to Author
        Author author = (Author) object;

        // Delete status
        boolean isDeleted = false;

        try{
            // SQL
            String sql = "DELETE FROM author WHERE id_author = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, author.getId_author());

            // Execute Query
            int totalRowAffected = preparedStatement.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The deleted was successful.");
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null,"Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return isDeleted;
    }
}
