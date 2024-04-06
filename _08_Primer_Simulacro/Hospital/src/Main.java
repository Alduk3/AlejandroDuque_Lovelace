import controller.AppointmentController;
import controller.DoctorController;
import controller.PatientController;
import controller.SpecialtyController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String option = "";

        do {
            option = JOptionPane.showInputDialog("""
                    1. Specialties.
                    2. Patients.
                    3. Doctors.
                    4. Appointments.
                    5. Search by category.
                    0. Exit.
                    
                    Chose an option:
                    """);
            switch (option) {
                case "1":
                    menu("specialty");
                    break;
                case "2":
                    menu("patient");
                    break;
                case "3":
                    menu("doctor");
                    break;
                case "4":
                    menu("appointment");
                    break;
                case "5":
                    int selection = JOptionPane.showOptionDialog(
                            null,
                            "Search : ",
                            "Categories",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new Object[] { "Appointment by Date", "Patient by Document", "Doctor by Specialty" },
                            "id");
                    if (selection == 0){
                        AppointmentController.getByDate();
                    } else if (selection == 1) {
                        PatientController.getByIdentification_document();
                    } else if (selection == 2) {
                        DoctorController.getBySpecialty();
                        
                    }
                    break;
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
                    case "specialty":
                        SpecialtyController.getAll();
                        break;
                    case "patient":
                        PatientController.getAll();
                        break;
                    case "doctor":
                        DoctorController.getAll();
                        break;
                    case "appointment":
                        AppointmentController.getAll();
                        break;
                }
                break;
            case "2":
                switch (category){
                    case "specialty":
                        SpecialtyController.create();
                        break;
                    case "patient":
                        PatientController.create();
                        break;
                    case "doctor":
                        DoctorController.create();
                        break;
                    case "appointment":
                        AppointmentController.create();
                        break;
                }
                break;
            case "3":
                switch (category){
                    case "specialty":
                        SpecialtyController.update();
                        break;
                    case "patient":
                        PatientController.update();
                        break;
                    case "doctor":
                        DoctorController.update();
                        break;
                    case "appointment":
                        AppointmentController.update();
                        break;
                }
                break;
            case "4":
                switch (category){
                    case "specialty":
                        SpecialtyController.delete();
                        break;
                    case "patient":
                        PatientController.delete();
                        break;
                    case "doctor":
                        DoctorController.delete();
                        break;
                    case "appointment":
                        AppointmentController.delete();
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