package controller;

import entity.Coder;
import entity.Company;
import entity.Hiring;
import entity.Vacant;
import model.HiringModel;

import javax.swing.*;
import java.util.List;

public class HiringController {

    public static void getAll(){
        // Use model
        HiringModel hiringModel = new HiringModel();

        // Create string list to hiring
        String hiringList = "Hiring list: \n";

        for (Object iterator : hiringModel.findAll()){
            // Convert iterator to hiring
            Hiring hiring = (Hiring) iterator;

            hiringList += iterator.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, hiringList);

    }

    public static void create(){
        // Use model
        HiringModel hiringModel = new HiringModel();

        try {


        // Select vacant
        Vacant vacant = VacantController.getVacantByStatus(true);

        try {
        // Select a coder
        Coder coder = CoderController.getCoderByTechnology(vacant.getTechnology());


            int selection = JOptionPane.showOptionDialog(
                    null,
                    "Status : ",
                    "Hiring status",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[] { "active", "inactive"},
                    "active");
            Double salary = Double.parseDouble(JOptionPane.showInputDialog("Enter salary: "));

            // Create Hiring
            Hiring hiring = new Hiring();

            // Set values
            hiring.setId_vacant(vacant.getId_vacant());
            hiring.setId_coder(coder.getId_coder());
            if (selection == 0){
                hiring.setStatus(true);
            } else {
                hiring.setStatus(false);
            }
            hiring.setSalary(salary);

            // call method
            hiring = (Hiring) hiringModel.insert(hiring);

            Company company = CompanyController.getById(vacant.getId_company());

            JOptionPane.showMessageDialog(null,vacant.toStringPersonalise()+ " " + company.toStringPersonalise() + " " + coder.toStringPersonalise() + " " +  hiring.getSalary());
            VacantController.updateStatus(vacant);
        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Coders not found to this vacant :( ");
        }
        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Vacant available not found");
        }

    }

    public static void update(){
        // Use model
        HiringModel hiringModel = new HiringModel();

        // Select hiring
        Hiring hiring = getHiring();

        // Select vacant
        Vacant vacant = VacantController.getVacant();

        Company company = CompanyController.getById(vacant.getId_company());

        if (vacant.getId_vacant() == hiring.getId_vacant()){
            try {
                // Select a coder
                Coder coder = CoderController.getCoderByTechnology(vacant.getTechnology());

                int selection = JOptionPane.showOptionDialog(
                        null,
                        "Status : ",
                        "Hiring status",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new Object[] { "active", "inactive"},
                        "active");
                Double salary = Double.parseDouble(JOptionPane.showInputDialog("Enter salary: "));

                // Set values
                hiring.setId_coder(coder.getId_coder());
                if (selection == 0){
                    hiring.setStatus(true);
                } else {
                    hiring.setStatus(false);
                }
                hiring.setSalary(salary);

                // call method
                hiringModel.update(hiring);

                JOptionPane.showMessageDialog(null,vacant.toStringPersonalise()+ " " + company.toStringPersonalise() + " " + coder.toStringPersonalise() + " " +  hiring.getSalary());

            } catch (Exception error){
                JOptionPane.showMessageDialog(null, "Coders not found to this vacant :( ");
            }
        } else if (vacant.isStatus()) {
            JOptionPane.showMessageDialog(null, "This vacant is not available");
        } else {


            try {
                VacantController.updateStatus(VacantController.getById(hiring.getId_vacant()));
                // Select a coder
                Coder coder = CoderController.getCoderByTechnology(vacant.getTechnology());

                int selection = JOptionPane.showOptionDialog(
                        null,
                        "Status : ",
                        "Hiring status",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new Object[] { "active", "inactive"},
                        "active");
                Double salary = Double.parseDouble(JOptionPane.showInputDialog("Enter salary: "));

                // Set values
                hiring.setId_vacant(vacant.getId_vacant());
                hiring.setId_coder(coder.getId_coder());
                if (selection == 0){
                    hiring.setStatus(true);
                } else {
                    hiring.setStatus(false);
                }
                hiring.setSalary(salary);

                // call method
                hiringModel.update(hiring);

                JOptionPane.showMessageDialog(null,vacant.toStringPersonalise()+ " " + company.toStringPersonalise() + " " + coder.toStringPersonalise() + " " +  hiring.getSalary());
                VacantController.updateStatus(vacant);

            } catch (Exception error){
                JOptionPane.showMessageDialog(null, "Coders not found to this vacant :( ");
            }

        }


    }

    public static void delete(){
        // Use model
        HiringModel hiringModel = new HiringModel();

        // Select hiring
        Hiring hiring = getHiring();

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete hiring : " + hiring);

        if (confirm == 0){
            VacantController.updateStatus(VacantController.getById(hiring.getId_vacant()));
            hiringModel.delete(hiring);
        }
    }

    public static Hiring getHiring(){
        // Use model
        HiringModel hiringModel = new HiringModel();

        // Create list to hiring
        List<Object> hiringList = hiringModel.findAll();

        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an hiring",
                "Hiring",
                JOptionPane.QUESTION_MESSAGE,
                null,
                hiringList.toArray(),
                hiringList.getFirst()
        );

        return (Hiring) option;
    }

}
