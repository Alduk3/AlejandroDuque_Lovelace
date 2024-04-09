import controller.CoderController;
import controller.CompanyController;
import controller.HiringController;
import controller.VacantController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String option;

        do {
            option = JOptionPane.showInputDialog("""
                    1. Coder.
                    2. Company.
                    3. Vacant.
                    4. Hiring.
                    5. Search by category.
                    
                    0. Exit
                    """);
            switch (option){
                case "1":
                    menu("coder");
                    break;
                case "2":
                    menu("company");


                    break;
                case "3":
                    menu("vacant");

                    break;
                case "4":
                    menu("hiring");

                    break;
                case "5":
                    int selection = JOptionPane.showOptionDialog(
                            null,
                            "Search : ",
                            "Categories",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new Object[] { "Vacant by title", "Vacant by technology", "Coder by cohort", "Coder by clan", "Coder by technology" },
                            "Vacant by title");

                    switch (selection){
                        case 0:
                            VacantController.getByTitle();
                            break;
                        case 1:
                            VacantController.getByTechnology();
                            break;
                        case 2:
                            CoderController.getByCohort();
                            break;
                        case 3:
                            CoderController.getByClan();
                            break;
                        case 4:
                            CoderController.getByTechnology();
                            break;
                    }
                    break;
                case null:
                    JOptionPane.showMessageDialog(null, "Good bye :)");
                    option = "0";
                    break;
                default:
                    System.out.println("Good Bye :)");
            }
        } while (!option.equals("0"));
    }

    public  static void menu(String category){
        String option = JOptionPane.showInputDialog(category+":\n"+"""
                    1. List.
                    2. Create.
                    3. Update.
                    4. Delete.
                    0. Back to Menu.
                    
                    Choose an option:
                    """);
        switch (option){
            case "1":
                switch (category){
                    case "coder":
                        CoderController.getAll();
                        break;
                    case "company":
                        CompanyController.getAll();
                        break;
                    case "vacant":
                        VacantController.getAll();
                        break;
                    case "hiring":
                        HiringController.getAll();
                        break;
                }
                break;
            case "2":
                switch (category){
                    case "coder":
                        CoderController.create();
                        break;
                    case "company":
                        JOptionPane.showMessageDialog(null, "You dont have permission");
                        break;
                    case "vacant":
                        VacantController.create();
                        break;
                    case "hiring":
                        HiringController.create();
                        break;
                }
                break;
            case "3":
                switch (category){
                    case "coder":
                        CoderController.update();
                        break;
                    case "company":
                        JOptionPane.showMessageDialog(null, "You dont have permission");
                        break;
                    case "vacant":
                        VacantController.update();
                        break;
                    case "hiring":
                        HiringController.update();
                        break;
                }
                break;
            case "4":
                switch (category){
                    case "coder":
                        CoderController.delete();
                        break;
                    case "company":
                        JOptionPane.showMessageDialog(null, "You dont have permission");
                        break;
                    case "vacant":
                        VacantController.delete();
                        break;
                    case "hiring":
                        HiringController.delete();
                        break;
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