package controller;

import entity.Author;
import model.AuthorModel;

import javax.swing.*;
import java.util.List;

public class AuthorController {
    public static void getAll(){
        // Use model
        AuthorModel authorModel = new AuthorModel();

        // Create string list
        String listAuthors = "List authors: \n";

        for (Object iterator: authorModel.findAll()){
            // Convert iterator to author
            Author author = (Author) iterator;

            listAuthors += author.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listAuthors);
    }

    public static void create(){
        // Use model
        AuthorModel authorModel = new AuthorModel();

        // Create new Author
        Author author = new Author();

        // Get info from user
        String name = JOptionPane.showInputDialog("Enter name: ");
        String nationality = JOptionPane.showInputDialog("Enter nationality: ");

        // Set info
        author.setName(name);
        author.setNationality(nationality);

        // Call method to insert and castled the object to save id in author
        author = (Author) authorModel.insert(author);

        JOptionPane.showMessageDialog(null, author.toString());
    }

    public static void update(){
        // Use model
        AuthorModel authorModel = new AuthorModel();

        // Select Author
        try {
            Author author = (Author) getAuthor();

            // Get updated info
            String name = JOptionPane.showInputDialog("Enter new name: ", author.getName());
            String nationality = JOptionPane.showInputDialog("Enter new nationality", author.getNationality());

            // Set new info
            author.setName(name);
            author.setNationality(nationality);

            // Call method to update
            authorModel.update(author);

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Author not found " + error.getMessage());
        }
    }

    public static void delete(){
        // Use model
        AuthorModel authorModel = new AuthorModel();

        // Select author
        try{
            Author author = (Author) getAuthor();

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want  to delete the book ? \n" + author.toString());

            if (confirm == 0) authorModel.delete(author);

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Author not found" + error.getMessage());
        }
    }

    public static Object getAuthor(){
        // Use model
        AuthorModel authorModel = new AuthorModel();

        // Get list Authors
        List<Object> listAuthors = authorModel.findAll();

        return JOptionPane.showInputDialog(
                null,
                "Select an author: ",
                "Authors",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listAuthors.toArray(),
                listAuthors.getFirst()
        );
    }
}
