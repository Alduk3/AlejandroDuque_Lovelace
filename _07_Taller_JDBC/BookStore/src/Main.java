import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String option = "";
        do {
            option = JOptionPane.showInputDialog("""
                    1. List.
                    2. Insert.
                    3. Update.
                    4. Delete.
                    5. Get books by author.
                    6. Exit
                    
                    Choose an option:
                    """);

            switch (option){
                case "1":
                    /*
                    *int selection = JOptionPane.showOptionDialog(
                            null,
                            "What do you want to list?",
                            null,
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new Object[] { "Books", "Authors"},   // null para YES, NO y CANCEL
                            "Books");
                    if (selection == 0){
                        System.out.println("List books");
                    } else if (selection == 1) {
                        System.out.println("List authors");
                    }*/
                    break;
                case "2":
                    System.out.println("Insert new coder");
                    break;
                case "3":
                    System.out.println("Update Coder");
                    break;
                case null:
                    System.out.println("Good Bye :)");
                    option = "6";
                    break;
                default:
                    System.out.println("Ou an error");
            }

        } while (!option.equals("6"));
    }
}