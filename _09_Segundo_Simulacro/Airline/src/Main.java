import controller.AirplaneController;
import controller.FlightController;
import controller.PassengerController;
import controller.ReservationController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String option ;
        do {
            option = JOptionPane.showInputDialog("""
                    1. Airplane.
                    2. Flight.
                    3. Passenger.
                    4. Reservation.
                    5. Search by category.
                    0. Exit.
                    
                    Choose an option:
                    """);
            switch (option){
                case "1":
                    menu("airplane");
                    break;
                case "2":
                    menu("flight");
                    break;
                case "3":
                    menu("passenger");
                    break;
                case "4":
                    menu("reservation");
                    break;
                case "5":
                    int selection = JOptionPane.showOptionDialog(
                            null,
                            "Search : ",
                            "Categories",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new Object[] { "Flight by destiny", "Passenger by name", "Reservations by flight" },
                            "id");
                    if (selection == 0){
                        FlightController.getByDestiny();
                    } else if (selection == 1) {
                        PassengerController.getByName();
                    } else if (selection == 2) {
                        ReservationController.getByFlight();
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
                    case "airplane":
                        AirplaneController.getAll();
                        break;
                    case "flight":
                        FlightController.getAll();
                        break;
                    case "passenger":
                        PassengerController.getAll();
                        break;
                    case "reservation":
                        ReservationController.getAll();
                        break;
                }
                break;
            case "2":
                switch (category){
                    case "airplane":
                        AirplaneController.create();
                        break;
                    case "flight":
                        FlightController.create();
                        break;
                    case "passenger":
                        PassengerController.create();
                        break;
                    case "reservation":
                        ReservationController.create();
                        break;
                }
                break;
            case "3":
                switch (category){
                    case "airplane":
                        AirplaneController.update();
                        break;
                    case "flight":
                        FlightController.update();
                        break;
                    case "passenger":
                        PassengerController.update();
                        break;
                    case "reservation":
                        ReservationController.update();
                        break;
                }
                break;
            case "4":
                switch (category){
                    case "airplane":
                        AirplaneController.delete();
                        break;
                    case "flight":
                        FlightController.delete();
                        break;
                    case "passenger":
                        PassengerController.delete();
                        break;
                    case "reservation":
                        ReservationController.delete();
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