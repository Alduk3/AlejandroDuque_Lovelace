package model;

import database.CRUD;
import database.ConfigDB;
import entity.Book;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookModel implements CRUD {
    @Override
    public Object insert(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to Book
        Book book = (Book) object;

        try{
            // SQL
            String sql = "INSERT INTO book(title, year_publication, price, id_author) VALUES (?,?,?,?);";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set value to ?
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getYear_publication());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setInt(4, book.getId_author());

            // Execute query
            preparedStatement.execute();

            // Get response
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                book.setId_book(resultSet.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Book insertion was  successful.");
        } catch (Exception error) {
            JOptionPane.showMessageDialog( null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return book;
    }

    @Override
    public List<Object> findAll() {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to Books
        List<Object> listBooks = new ArrayList<>();

        try{
            // SQL
            String sql = "SELECT * FROM book;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                // Create book
                Book book = new Book();

                // Set data
                book.setId_book(resultSet.getInt("id_book"));
                book.setId_author(resultSet.getInt("id_author"));
                book.setTitle(resultSet.getString("title"));
                book.setYear_publication(resultSet.getInt("year_publication"));
                book.setPrice(resultSet.getDouble("price"));

                // Add book to list
                listBooks.add(book);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return listBooks;
    }

    @Override
    public boolean update(Object object) {
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Convert object to Book
        Book book = (Book) object;

        // Update status
        boolean isUpdated = false;

        try {
            // SQL
            String sql = "UPDATE book SET title = ?, year_publication = ?, price = ?, id_author = ? WHERE id_book = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getYear_publication());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setInt(4, book.getId_author());
            preparedStatement.setInt(5, book.getId_book());

            // Execute Query
            int totalAffectedRow = preparedStatement.executeUpdate();

            if (totalAffectedRow > 0){
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

        // Convert object to book
        Book book = (Book) object;

        // Delete status
        boolean isDeleted = false;

        try{
            // SQL
            String sql = "DELETE FROM book WHERE id_book = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, book.getId_book());

            // Execute query
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

    public Book findById(int id_book){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create a book
        Book book = null;

        try {
            // SQL
            String sql = "SELECT * FROM book WHERE id_book = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, id_book);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                book = new Book();

                // Set data to book
                book.setId_book(resultSet.getInt("id_book"));
                book.setTitle(resultSet.getString("title"));
                book.setYear_publication(resultSet.getInt("year_publication"));
                book.setPrice(resultSet.getDouble("price"));
                book.setId_author(resultSet.getInt("id_author"));

            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return book;
    }

    public List<Book> findByTitle(String title_book){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to books
        List<Book> listBooks = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM book WHERE title LIKE ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setString(1,"%"+title_book+"%");

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create a book
                Book book = new Book();

                // Set data to book
                book.setId_book(resultSet.getInt("id_book"));
                book.setTitle(resultSet.getString("title"));
                book.setYear_publication(resultSet.getInt("year_publication"));
                book.setPrice(resultSet.getDouble("price"));
                book.setId_author(resultSet.getInt("id_author"));

                // Add to list
                listBooks.add(book);
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return listBooks;
    }

    public List<Book> findByAuthor(int id_author){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to books
        List<Book> listBooks = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM book WHERE id_author = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value to ?
            preparedStatement.setInt(1, id_author);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create a book
                Book book = new Book();

                // Set data to book
                book.setId_book(resultSet.getInt("id_book"));
                book.setTitle(resultSet.getString("title"));
                book.setYear_publication(resultSet.getInt("year_publication"));
                book.setPrice(resultSet.getDouble("price"));
                book.setId_author(resultSet.getInt("id_author"));

                // Add to list
                listBooks.add(book);
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return listBooks;
    }
}
