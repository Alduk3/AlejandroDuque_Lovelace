package controller;

import entity.Company;
import entity.Vacant;
import model.VacantModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VacantController {
    public static void getAll(){
        // Use model
        VacantModel vacantModel = new VacantModel();

        // Create string list to vacant
        String vacantList = "Vacant list: \n";

        for (Object iterator : vacantModel.findAll()){
            // Convert iterator to vacant
            Vacant vacant = (Vacant) iterator;

            vacantList += iterator.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, vacantList);
    }

    public static void create(){
        // Use model
        VacantModel vacantModel = new VacantModel();

        // Select company
        Company company = CompanyController.getCompany();

        // Get info from user
        String title = JOptionPane.showInputDialog("Enter title: ");
        String description = JOptionPane.showInputDialog("Enter description: ");
        String duration = JOptionPane.showInputDialog("Enter duration: ");
        int selection = JOptionPane.showOptionDialog(
                null,
                "Status : ",
                "Categories",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[] { "active", "inactive"},
                "active");
        String technology = JOptionPane.showInputDialog("Enter technology: ");

        // Create vacant
        Vacant vacant = new Vacant();

        vacant.setId_company(company.getId_company());
        vacant.setTitle(title);
        vacant.setDescription(description);
        vacant.setDuration(duration);
        if (selection == 0){
            vacant.setStatus(true);
        } else {
            vacant.setStatus(false);
        }
        vacant.setTechnology(technology);

        // Call method to insert and get id_vacant
        vacant = (Vacant) vacantModel.insert(vacant);

        JOptionPane.showMessageDialog(null, vacant);
    }

    public static void update(){
        // Use model
        VacantModel vacantModel = new VacantModel();

        // Select a vacant
        Vacant vacant = getVacant();

        // Get update info
        Company company = CompanyController.getCompany();

        String title = JOptionPane.showInputDialog("Enter new title", vacant.getTitle());
        String description = JOptionPane.showInputDialog("Enter new description", vacant.getDescription());
        String duration = JOptionPane.showInputDialog("Enter duration: ", vacant.getDuration());
        int selection = JOptionPane.showOptionDialog(
                null,
                " Status : ",
                "Vacant status",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[] { "active", "inactive"},
                "active");
        String technology = JOptionPane.showInputDialog("Enter technology: ", vacant.getTechnology());

        vacant.setId_company(company.getId_company());
        vacant.setTitle(title);
        vacant.setDescription(description);
        vacant.setDuration(duration);
        if (selection == 0){
            vacant.setStatus(true);
        } else {
            vacant.setStatus(false);
        }
        vacant.setTechnology(technology);

        // Call method to update
        vacantModel.update(vacant);

        JOptionPane.showMessageDialog(null, vacant);
    }

    public static void delete(){
        // Use model
        VacantModel vacantModel = new VacantModel();

        // Select vacant
        Vacant vacant = getVacant();

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete vacant: " + vacant);

        if (confirm == 0) vacantModel.delete(vacant);
    }

    public static Vacant getVacant(){
        // Use model
        VacantModel vacantModel = new VacantModel();

        // Create list to select
        List<Object> vacantList = vacantModel.findAll();

        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an vacant",
                "Vacant",
                JOptionPane.QUESTION_MESSAGE,
                null,
                vacantList.toArray(),
                vacantList.getFirst()
        );

        return (Vacant) option;
    }

    public static Vacant getVacantByStatus(boolean status){
        // Use model
        VacantModel vacantModel = new VacantModel();

        // Create array to vacant
        List<Vacant> vacantList = vacantModel.findByStatus(status);

        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an vacant",
                "Vacant available",
                JOptionPane.QUESTION_MESSAGE,
                null,
                vacantList.toArray(),
                vacantList.getFirst()
        );
        return (Vacant) option;
    }

    public static boolean updateStatus(Vacant vacant){
        // Use model
        VacantModel vacantModel = new VacantModel();

        return vacantModel.updateStatus(vacant);
    }

    public static Vacant getById(int id_vacant){
        // Use model
        VacantModel vacantModel = new VacantModel();
        Vacant vacant = null;
        try {
            vacant = vacantModel.findById(id_vacant);
        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Vacant not found");
        }
        return vacant;
    }

    public static void getByTechnology(){
        // Use model
        VacantModel vacantModel = new VacantModel();

        // Enter technology
        String technology = JOptionPane.showInputDialog("Enter technology: ");

        // Create string list to vacant
        String vacantList = "Vacant list: \n";

        // is empty?
        boolean isEmpty = true;

            for (Vacant iterator : vacantModel.findByTechnology(technology)){
                isEmpty = false;
                vacantList += iterator.toString() + "\n";
            }

            if (!isEmpty){
                JOptionPane.showMessageDialog(null, vacantList);
            } else {
                JOptionPane.showMessageDialog(null, "Vacant not found by "+ technology);
            }


    }

    public static void getByTitle(){
        // Use model
        VacantModel vacantModel = new VacantModel();

        // Enter technology
        String title = JOptionPane.showInputDialog("Enter title: ");

        // Create string list to vacant
        String vacantList = "Vacant list: by" + title + "\n";

        // is empty?
        boolean isEmpty = true;

            for (Vacant iterator : vacantModel.findByTitle(title)){
                isEmpty = false;
                vacantList += iterator.toString() + "\n";
            }

            if (!isEmpty){
                JOptionPane.showMessageDialog(null, vacantList);

            } else {
                JOptionPane.showMessageDialog(null, "Vacant not found by "+ title);

            }


    }
}
