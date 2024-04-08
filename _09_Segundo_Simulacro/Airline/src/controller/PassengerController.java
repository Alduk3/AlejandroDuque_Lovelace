package controller;

import entity.Passenger;
import model.PassengerModel;

import javax.swing.*;
import java.util.List;

public class PassengerController {
    public static void getAll(){
        // Use model
        PassengerModel passengerModel = new PassengerModel();

        // Create string list to passenger
        String passengerList = "Passenger list: \n";

        for (Object iterator : passengerModel.findAll()){
            // Convert iterator to passenger
            Passenger passenger = (Passenger) iterator;

            passengerList += passenger.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, passengerList);
    }

    public static void create(){
        // Use model
        PassengerModel passengerModel = new PassengerModel();

        // Get info from user
        String name = JOptionPane.showInputDialog("Enter name: ");
        String last_name = JOptionPane.showInputDialog("Enter last_name: ");
        String identification_document = JOptionPane.showInputDialog("Enter identification_document: ");

        // Create Passenger
        Passenger passenger = new Passenger();

        passenger.setName(name);
        passenger.setLast_name(last_name);
        passenger.setIdentification_document(identification_document);

        // Call method to insert and get back the id
        passenger = (Passenger) passengerModel.insert(passenger);

        JOptionPane.showMessageDialog(null, passenger);
    }

    public static void update(){
        // Use model
        PassengerModel passengerModel = new PassengerModel();

        // Select Passenger
        Passenger passenger = getPassenger();

        // Get update info
        String name = JOptionPane.showInputDialog("Enter new name: ", passenger.getName());
        String last_name = JOptionPane.showInputDialog("Enter new lastname: ", passenger.getLast_name());
        String identification_document = JOptionPane.showInputDialog("Enter new identification document: ", passenger.getIdentification_document());

        // Set new info
        passenger.setName(name);
        passenger.setLast_name(last_name);
        passenger.setIdentification_document(identification_document);

        // Call method to update
        passengerModel.update(passenger);
    }

    public static void delete(){
        // Use model
        PassengerModel passengerModel = new PassengerModel();

        // Select passenger
        Passenger passenger = getPassenger();

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the passenger? \n" + passenger.toString());
        if (confirm == 0) passengerModel.delete(passenger);
    }

    public static Passenger getPassenger(){
        // Use model
        PassengerModel passengerModel = new PassengerModel();

        List<Object> passengerList = passengerModel.findAll();

        // Select passenger
        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an passenger",
                "Passengers",
                JOptionPane.QUESTION_MESSAGE,
                null,
                passengerList.toArray(),
                passengerList.getFirst()
        );

        return (Passenger) option;
    }

    public static void getByName(){
        // Use model
        PassengerModel passengerModel = new PassengerModel();

        // Get name
        String name = JOptionPane.showInputDialog("Enter name: ");

        // Create list
        String passengersList = "Passengers by " + name + "\n";

        // isEmpty
        boolean isEmpty = true;

        for (Passenger iterator : passengerModel.findByName(name)){
            isEmpty = false;
            passengersList += iterator.toString() + "\n";
        }

        if (!isEmpty){
            JOptionPane.showMessageDialog(null, passengersList);
        } else {
            JOptionPane.showMessageDialog(null, "Passengers by " + name + " not found :(");
        }
    }
}
