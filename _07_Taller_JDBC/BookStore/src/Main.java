import controller.AuthorController;
import controller.BookController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String option = "";
        do {
            option = JOptionPane.showInputDialog("""
                    1. Authors.
                    2. Books.
                    3. Search book by category.
                    0. Exit
                    
                    Choose an option:
                    """);

            switch (option){
                case "1":
                    menu("a");
                    break;
                case "2":
                   menu("b");
                    break;
                case "3":
                    int selection = JOptionPane.showOptionDialog(
                            null,
                            "Search book by: ",
                            "Categories",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new Object[] { "Id", "Title", "Author" },
                            "id");

                    if (selection == 0){
                        BookController.getById();
                    } else if (selection == 1) {
                        BookController.getByTitle();
                    } else if (selection == 2) {
                        BookController.getByAuthor();
                    }
                    break;
                case null:
                    System.out.println("Good Bye :)");
                    option = "0";
                    break;
                default:
                    System.out.println("Good Bye :)");
            }

        } while (!option.equals("0"));
    }
    public static void menu(String category){

        String option = JOptionPane.showInputDialog("""
                    1. List.
                    2. Create.
                    3. Update.
                    4. Delete.
                    0. Back to Menu.
                    
                    Choose an option:
                    """);
        switch (option){
            case "1":
                if (category.equals("a")){
                    AuthorController.getAll();
                } else {
                    BookController.getAll();
                }
                break;
            case "2":
                if (category.equals("a")){
                    AuthorController.create();
                }else {
                    BookController.create();
                }
                break;
            case "3":
                if (category.equals("a")){
                    AuthorController.update();
                } else {
                    BookController.update();
                }
                break;
            case "4":
                if (category.equals("a")){
                    AuthorController.delete();
                } else {
                    BookController.delete();
                }
                break;
            case null:
                System.out.println("Good Bye :)");
                option = "0";
                break;
            default:
                System.out.println("Good Bye :)");
        }
    }
}