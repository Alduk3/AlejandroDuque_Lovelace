package controller;

import entity.Author;
import entity.Book;
import model.BookModel;

import javax.swing.*;
import java.util.List;

public class BookController {
    public static void getAll(){
        // Use model
        BookModel bookModel = new BookModel();

        // Create string list to book
        String listBooks = "List books: \n";

        for (Object iterator: bookModel.findAll()){
            // Convert iterator to book
            Book book = (Book) iterator;

            listBooks += book.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listBooks);
    }

    public static void create(){
        // Use model
        BookModel bookModel = new BookModel();

        // Create new Book
        Book book = new Book();

        // Get data from user
        try{
            Author author = (Author) AuthorController.getAuthor();

            int id_author = author.getId_author();
            String title = JOptionPane.showInputDialog("Enter title: ");
            int year_publication = Integer.parseInt(JOptionPane.showInputDialog("Enter year of publication: "));
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter price: "));

            // Set data to Book
            book.setId_author(id_author);
            book.setTitle(title);
            book.setYear_publication(year_publication);
            book.setPrice(price);

            // Call method to insert and castled the object to save id in book
            book = (Book) bookModel.insert(book);

            JOptionPane.showMessageDialog(null, book.toString());

        } catch (Exception error ){
            JOptionPane.showMessageDialog(null, "Author not found" + error.getMessage());
        }
    }

    public static void update(){
        // Use model
        BookModel bookModel = new BookModel();

        // Select a book
        try {
            Book book = (Book) getBook();

            // Get update info
            String title = JOptionPane.showInputDialog("Enter new title: ", book.getTitle());
            int year_publication = Integer.parseInt(JOptionPane.showInputDialog("Enter new year of publication", book.getYear_publication()));
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter new price: ", book.getPrice()));

            Author author = (Author) AuthorController.getAuthor();
            int id_author = author.getId_author();

            // Set new info
            book.setTitle(title);
            book.setYear_publication(year_publication);
            book.setPrice(price);
            book.setId_author(id_author);

            // Call method to update
            bookModel.update(book);

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        }

    }

    public static void delete(){
        // Use model
        BookModel bookModel = new BookModel();

        // Select book
        try{
            Book book = (Book) getBook();

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the book? \n" + book.toString());

            if (confirm == 0) bookModel.delete(book);

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        }
    }

    public static Object getBook(){
        // Use model
        BookModel bookModel = new BookModel();

        List<Object> listBooks = bookModel.findAll();

        return JOptionPane.showInputDialog(
                null,
                "Select an author: ",
                "Authors",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listBooks.toArray(),
                listBooks.getFirst()
        );
    }

    public static void getById(){
        // Use model
        BookModel bookModel = new BookModel();

        int id_book = Integer.parseInt(JOptionPane.showInputDialog("Enter id: "));

        Book book = bookModel.findById(id_book);

        if (book == null){
            JOptionPane.showMessageDialog(null, "Book not found");
        } else {
            JOptionPane.showMessageDialog(null, book.toString());
        }

    }

    public static void getByTitle(){
        // Use model
        BookModel bookModel = new BookModel();

        // Get title
        String title = JOptionPane.showInputDialog("Enter title: ");

        // Create string list to books
        String listBooks = "Books to "+ title+ "\n";

        // isEmpty
        boolean isEmpety = true;

        for (Book iterator : bookModel.findByTitle(title)){
            isEmpety = false;
            listBooks += iterator.toString() + "\n";
        }

        if (!isEmpety){
            JOptionPane.showMessageDialog(null, listBooks);
        } else {
            JOptionPane.showMessageDialog(null, "Book not found");
        }
    }

    public static void getByAuthor(){
        // Use model
        BookModel bookModel = new BookModel();

        // Get Author
        try {
            Author author = (Author) AuthorController.getAuthor();

            // Create string list to books
            String listBooks = "Books by " + author.getName() + "\n";

            for (Book iterator : bookModel.findByAuthor(author.getId_author())){
                listBooks += iterator.toString() + "\n";
            }

            JOptionPane.showMessageDialog(null, listBooks);

        } catch (Exception error ){
            JOptionPane.showMessageDialog(null, "Author not found" + error.getMessage());
        }

    }
}
