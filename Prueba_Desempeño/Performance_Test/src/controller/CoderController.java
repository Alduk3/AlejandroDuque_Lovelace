package controller;

import entity.Coder;
import model.CoderModel;

import javax.swing.*;
import java.util.List;

public class CoderController {

    public static void getAll(){
        // Use model
        CoderModel coderModel = new CoderModel();

        // Create string list to coders
        String coderList = "Coder list: \n";

        for (Object iterator : coderModel.findAll()){
            // Convert iterator to coder
            Coder coder = (Coder) iterator;

            coderList += coder.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, coderList);
    }

    public static void create(){
        // Use model
        CoderModel coderModel = new CoderModel();

        // Get info from user
        String name = JOptionPane.showInputDialog("Enter name: ");
        String lastName = JOptionPane.showInputDialog("Enter lastname: ");
        String document = JOptionPane.showInputDialog("Enter document: ");
        int cohort = Integer.parseInt(JOptionPane.showInputDialog("Enter cohort: "));
        String cv = JOptionPane.showInputDialog("Enter cv: ");
        String clan = JOptionPane.showInputDialog("Enter clan: ");

        // Create new coder
        Coder coder = new Coder();

        // Set values to coder
        coder.setName(name);
        coder.setLastname(lastName);
        coder.setDocument(document);
        coder.setCohort(cohort);
        coder.setCv(cv);
        coder.setClan(clan);

        // Call method to create
        coder = (Coder) coderModel.insert(coder);

        JOptionPane.showMessageDialog(null, coder);
    }

    public static void update(){
        // Use model
        CoderModel coderModel = new CoderModel();

        // Select coder
        Coder coder = getCoder();

        // Get updated info
        String name = JOptionPane.showInputDialog("Enter new name: ", coder.getName());
        String lastName = JOptionPane.showInputDialog("Enter new lastname: ", coder.getLastname());
        String document = JOptionPane.showInputDialog("Enter new document: ", coder.getDocument());
        int cohort = Integer.parseInt(JOptionPane.showInputDialog("Enter new cohort: ", coder.getCohort()));
        String cv = JOptionPane.showInputDialog("Enter new cv: ", coder.getCv());
        String clan = JOptionPane.showInputDialog("Enter new clan: ", coder.getClan());

        // Set values to coder
        coder.setName(name);
        coder.setLastname(lastName);
        coder.setDocument(document);
        coder.setCohort(cohort);
        coder.setCv(cv);
        coder.setClan(clan);

        // Call method to update
        coderModel.update(coder);

        JOptionPane.showMessageDialog(null, coder);
    }

    public static void delete(){
        // Use model
        CoderModel coderModel = new CoderModel();

        // Select coder
        Coder coder = getCoder();

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete the coder :" + coder);

        if (confirm == 0) coderModel.delete(coder);
    }

    public static Coder getCoder(){
        // Use model
        CoderModel coderModel = new CoderModel();

        // Create coder list
        List<Object> coderList = coderModel.findAll();

        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an coder: ",
                "Coders",
                JOptionPane.QUESTION_MESSAGE,
                null,
                coderList.toArray(),
                coderList.getFirst()
        );

        return (Coder) option;
    }

    public static Coder getCoderByTechnology(String technology){
        // Use model
        CoderModel coderModel = new CoderModel();

        // Create coder list
        List<Coder> coderList = coderModel.findByTechnology(technology);

        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an coder: ",
                "Coders",
                JOptionPane.QUESTION_MESSAGE,
                null,
                coderList.toArray(),
                coderList.getFirst()
        );

        return (Coder) option;
    }

    public static void getByTechnology(){
        // Use model
        CoderModel coderModel = new CoderModel();

        // Technology
        String technology = JOptionPane.showInputDialog("Enter technology: ");

        // Create string list to coders
        String coderList = "Coder list: " + technology + "\n";

        // Is empty?
        boolean isEmpty = true;

        for (Coder iterator : coderModel.findByTechnology(technology)){
            isEmpty = false;
            coderList += iterator.toString() + "\n";
        }

        if (!isEmpty){
            JOptionPane.showMessageDialog(null, coderList);
        } else {
            JOptionPane.showMessageDialog(null, "Coders not found by " + technology);
        }

    }
    public static void getByClan(){
        // Use model
        CoderModel coderModel = new CoderModel();

        // Technology
        String clan = JOptionPane.showInputDialog("Enter clan: ");

        // Create string list to coders
        String coderList = "Coder list: " + clan + "\n";

        // Is empty?
        boolean isEmpty = true;

        for (Coder iterator : coderModel.findByClan(clan)){
            isEmpty = false;
            coderList += iterator.toString() + "\n";
        }

        if (!isEmpty){
            JOptionPane.showMessageDialog(null, coderList);
        } else {
            JOptionPane.showMessageDialog(null, "Coders not found by " + clan);
        }

    }
    public static void getByCohort(){
        // Use model
        CoderModel coderModel = new CoderModel();

        // Technology
        int cohort = Integer.parseInt(JOptionPane.showInputDialog("Enter cohort: "));

        // Create string list to coders
        String coderList = "Coder list: " + cohort + "\n";

        // Is empty?
        boolean isEmpty = true;

        for (Coder iterator : coderModel.findByCohort(cohort)){
            isEmpty = false;
            coderList += iterator.toString() + "\n";
        }

        if (!isEmpty){
            JOptionPane.showMessageDialog(null, coderList);
        } else {
            JOptionPane.showMessageDialog(null, "Coders not found by " + cohort);
        }

    }
}
