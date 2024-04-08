package controller;

import entity.Airplane;
import model.AirplaneModel;

import javax.swing.*;
import java.util.List;

public class AirplaneController {
    public static void getAll(){
        // Use model
        AirplaneModel airplaneModel = new AirplaneModel();

        // Create string list ti save info
        String airplaneList = "Airplanes list \n";

        for (Object iterator : airplaneModel.findAll()){
            // Convert iterator to airplane
            Airplane airplane = (Airplane) iterator;
            airplaneList += airplane.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, airplaneList);
    };

    public static void create(){
        // Use model
        AirplaneModel airplaneModel = new AirplaneModel();

        // Get info to airplane
        String model = JOptionPane.showInputDialog("Enter model: ");
        int capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter capacity: "));

        // Create new airplane
        Airplane airplane = new Airplane();

        airplane.setModel(model);
        airplane.setCapacity(capacity);

        // Call method to insert and save id
        airplane = (Airplane) airplaneModel.insert(airplane);

        JOptionPane.showMessageDialog(null, airplane);
    };

    public static  void update(){
        // Use model
        AirplaneModel airplaneModel = new AirplaneModel();


        Airplane airplane = getAirplane();

        // Get info to update
        String model = JOptionPane.showInputDialog("Enter new model: ", airplane.getModel());
        int capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter new capacity: ", airplane.getCapacity()));

        // Set new info
        airplane.setModel(model);
        airplane.setCapacity(capacity);

        // Call method to update new info
        airplaneModel.update(airplane);
    }

    public static void delete(){
        // Use model
        AirplaneModel airplaneModel = new AirplaneModel();

        // Select airplane to delete
        Airplane airplane = getAirplane();

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the airplane? " + airplane.toString());

        if (confirm == 0) airplaneModel.delete(airplane);
    }
    public static Airplane getAirplane(){
        // Use model
        AirplaneModel airplaneModel = new AirplaneModel();

        // Select airplane
        List<Object> airplaneList = airplaneModel.findAll();

        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an airplane: ",
                "Airplanes",
                JOptionPane.QUESTION_MESSAGE,
                null,
                airplaneList.toArray(),
                airplaneList.getFirst()
        );

        return (Airplane) option;
    };

    public static int getCapacity(int id_airplane){
        // Use model
        AirplaneModel airplaneModel = new AirplaneModel();

        return airplaneModel.findCapacity(id_airplane);
    }
}
