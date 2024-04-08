package controller;

import entity.Airplane;
import entity.Flight;
import model.FlightModel;

import javax.swing.*;
import java.util.List;

public class FlightController {

    public static void getAll(){
        // Use model
        FlightModel flightModel = new FlightModel();

        // Create string list
        String flightList = "Flight list: \n";

        for (Object iterator : flightModel.findAll()){
            // Convert iterator to flight
            Flight flight = (Flight) iterator;

            flightList += iterator.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, flightList);
    };

    public static void create(){
        // Use model
        FlightModel flightModel = new FlightModel();

        // Get info from user
        String destiny = JOptionPane.showInputDialog("Enter destiny: ");
        String departure_date = JOptionPane.showInputDialog("Enter departure date");
        String departure_time = JOptionPane.showInputDialog("Enter departure time: ");

        // Get airplane
        Airplane airplane = AirplaneController.getAirplane();

        // Create flight
        Flight flight = new Flight();

        // Set info
        flight.setDestiny(destiny);
        flight.setDeparture_date(departure_date);
        flight.setDeparture_time(departure_time);
        flight.setId_airplane(airplane.getId_airplane());

        // Call method to insert and get the id
        flight = (Flight) flightModel.insert(flight);

        JOptionPane.showMessageDialog(null, flight.toString());
    }

    public static void update(){
        // Use model
        FlightModel flightModel = new FlightModel();

        // Select flight
        Flight flight = getFlight();

        // Get update info
        String destiny = JOptionPane.showInputDialog("Enter new destiny: ", flight.getDestiny());
        String departure_date = JOptionPane.showInputDialog("Enter new departure date: ", flight.getDeparture_date());
        String departure_time = JOptionPane.showInputDialog("Enter new departure time: ", flight.getDeparture_time());

        // Get airplane
        Airplane airplane = AirplaneController.getAirplane();

        // Set info
        flight.setDestiny(destiny);
        flight.setDeparture_date(departure_date);
        flight.setDeparture_time(departure_time);
        flight.setId_airplane(airplane.getId_airplane());

        // Call method to update
        flightModel.update(flight);

        JOptionPane.showMessageDialog(null, flight);
    }

    public static void delete(){
        // Use model
        FlightModel flightModel = new FlightModel();

        // Select flight
        Flight flight = getFlight();

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the flight? \n" + flight.toString());

        if (confirm == 0) flightModel.delete(flight);
    }

    public static Flight getFlight(){
        // Use model
        FlightModel flightModel = new FlightModel();

        List<Object> flightList = flightModel.findAll();

        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an flight",
                "Flights",
                JOptionPane.QUESTION_MESSAGE,
                null,
                flightList.toArray(),
                flightList.getFirst()
        );
        Flight flight = (Flight) option;
        return flight;
    }

    public static void getByDestiny(){
        // Use model
        FlightModel flightModel = new FlightModel();

        // Get destiny
        String destiny = JOptionPane.showInputDialog("Enter destiny: ");

        // Create string list
        String flightList = "Flights to " + destiny + "\n";

        // isEmpty
        boolean isEmpty = true;

        for (Flight iterator : flightModel.findByDestiny(destiny)){
            isEmpty = false;
            flightList += iterator.toString() + "\n";
        }

        if (!isEmpty){
            JOptionPane.showMessageDialog(null, flightList);
        } else {
            JOptionPane.showMessageDialog(null, "Flights not found to " + destiny + " :(");
        }
    }
}
