package controller;

import entity.Specialty;
import model.SpecialtyModel;

import javax.swing.*;
import java.util.List;

public class SpecialtyController {

    public static void getAll(){
        String listSpecialties = getAllString();

        JOptionPane.showMessageDialog(null, listSpecialties);
    }

    public static String getAllString(){
        SpecialtyModel specialtyModel = new SpecialtyModel();
        String listSpecialties = "🤷‍♂️ SPECIALTIES LIST \n";

        for (Object iterator: specialtyModel.findAll()){
            Specialty specialty = (Specialty) iterator;

            listSpecialties += specialty.toStringComplete() + "\n";
        }

        return listSpecialties;
    }

    public static void create(){
        // use model
        SpecialtyModel specialtyModel = new SpecialtyModel();

        // input info to user
        String name = JOptionPane.showInputDialog("Insert name: ");
        String description = JOptionPane.showInputDialog("Insert description: ");

        // Create specialty
        Specialty specialty = new Specialty();
        specialty.setName(name);
        specialty.setDescription(description);

        // call method to insertion
        specialty = (Specialty) specialtyModel.insert(specialty);

        JOptionPane.showMessageDialog(null, specialty.toString());

    }

    public static void update(){
        // use model
        SpecialtyModel specialtyModel = new SpecialtyModel();

        // Get specialties list
        List<Object> listSpecialties = specialtyModel.findAll();

        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an option",
                "Specialties",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listSpecialties.toArray(),
                listSpecialties.getFirst()

        );

        if (option == null){
            JOptionPane.showMessageDialog(null, "Specialty not found");
        } else {
            Specialty specialty = (Specialty) option;

            String name = JOptionPane.showInputDialog(null, "Enter new name: ", specialty.getName());
            String description = JOptionPane.showInputDialog(null, "Enter new description: ", specialty.getDescription());

            specialty.setName(name);
            specialty.setDescription(description);

            specialtyModel.update(specialty);
        }

    }

    public static void delete(){
        // use model
        SpecialtyModel specialtyModel = new SpecialtyModel();

        List<Object> listSpecialties = specialtyModel.findAll();

        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an option",
                "Specialties",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listSpecialties.toArray(),
                listSpecialties.getFirst()

        );

        if (option == null){
            JOptionPane.showMessageDialog(null, "Specialty not found");
        } else {
            Specialty specialty = (Specialty) option;
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want  to delete the specialty ? \n" + specialty.toString());

            if (confirm == 0) specialtyModel.delete(specialty);
        }
    }
}
