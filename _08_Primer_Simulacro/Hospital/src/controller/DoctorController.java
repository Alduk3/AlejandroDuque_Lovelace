package controller;

import entity.Doctor;
import entity.Specialty;
import model.DoctorModel;
import model.SpecialtyModel;

import javax.swing.*;
import java.util.List;

public class DoctorController {

    public static void getAll(){
        String listDoctors = getAllString();
        JOptionPane.showMessageDialog(null, listDoctors);
    }

    public static String getAllString(){
        DoctorModel doctorModel = new DoctorModel();
        String listDoctors = "Doctors List \n";

        for(Object iterator: doctorModel.findAll()){
            Doctor doctor = (Doctor) iterator;
            listDoctors += doctor.toString() + "\n";
        }
        return listDoctors;
    }

    public static void create(){
        // use model
        DoctorModel doctorModel = new DoctorModel();
        SpecialtyModel specialtyModel = new SpecialtyModel();

        List<Object> listSpecialties = specialtyModel.findAll();

        // Info user
        String name = JOptionPane.showInputDialog("Insert name: ");
        String last_name = JOptionPane.showInputDialog("Insert lastname: ");
        // int id_specialty =

        // get id from specialty
        Specialty specialty = (Specialty) JOptionPane.showInputDialog(
                null,
                "Choose an option",
                "Specialties",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listSpecialties.toArray(),
                listSpecialties.getFirst()

        );

        int id_specialty = specialty.getId_specialty();

        // Create doctor
        Doctor doctor = new Doctor();

        doctor.setName(name);
        doctor.setLast_name(last_name);
        doctor.setId_specialty(id_specialty);

        // Call to method to insertion
        doctor = (Doctor) doctorModel.insert(doctor);

        JOptionPane.showMessageDialog(null, doctor.toString());


    }

    public static void update() {
        // use model
        DoctorModel doctorModel = new DoctorModel();

        // Get Doctor list
        List<Object> listDoctors = doctorModel.findAll();

        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an option",
                "Doctors",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listDoctors.toArray(),
                listDoctors.getFirst()

        );

        if (option == null) {
            JOptionPane.showMessageDialog(null, "Doctor not found");
        } else {
            Doctor doctor = (Doctor) option;
            SpecialtyModel specialtyModel = new SpecialtyModel();

            List<Object> listSpecialties = specialtyModel.findAll();

            // Input data
            String name = JOptionPane.showInputDialog("Enter new name: ", doctor.getName());
            String last_name = JOptionPane.showInputDialog("Enter new lastname: ", doctor.getLast_name());

            // get Specialty
            Specialty specialty = (Specialty) JOptionPane.showInputDialog(
                    null,
                    "Choose an option",
                    "Specialties",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    listSpecialties.toArray(),
                    listSpecialties.getFirst()

            );

            int id_specialty = specialty.getId_specialty();


            // Set data
            doctor.setName(name);
            doctor.setLast_name(last_name);
            doctor.setId_specialty(id_specialty);

            // update data
            doctorModel.update(doctor);
        }
    }

    public static void delete(){
        // Use model
        DoctorModel doctorModel = new DoctorModel();

        // Get Doctor list
        List<Object> listDoctors = doctorModel.findAll();

        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an option",
                "Doctors",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listDoctors.toArray(),
                listDoctors.getFirst()

        );

        if (option == null) {
            JOptionPane.showMessageDialog(null, "Doctor not found");
        } else {
            Doctor doctor = (Doctor) option;

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want  to delete the Doctor? \n" + doctor.toString());

            if (confirm == 0 ) doctorModel.delete(doctor);
        }
    }

    public static void getBySpecialty(){
        // Use model
        DoctorModel doctorModel = new DoctorModel();
        SpecialtyModel specialtyModel = new SpecialtyModel();

        // Get id Specialty
        List<Object> listSpecialties = specialtyModel.findAll();

        Specialty specialty = (Specialty) JOptionPane.showInputDialog(
                null,
                "Choose an option",
                "Specialties",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listSpecialties.toArray(),
                listSpecialties.getFirst()

        );

        int id_specialty = specialty.getId_specialty();

        // Create string list to doctor
        String doctorList = "Doctor list on " + specialty.getName() + "\n";

        // isEmpty
        boolean isEmpty = true;

        for (Doctor iterator : doctorModel.findBySpecialty(id_specialty)){
            isEmpty = false;
            doctorList += iterator.toString() + "\n";
        }

        if (!isEmpty) {
            JOptionPane.showMessageDialog(null,doctorList);
        } else {
            JOptionPane.showMessageDialog(null, "Doctor not found");
        }
    }
}
