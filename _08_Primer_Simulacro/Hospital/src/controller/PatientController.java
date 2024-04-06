package controller;

import entity.Patient;
import entity.Specialty;
import model.PatientModel;

import javax.swing.*;
import java.util.List;

public class PatientController {
    public static void getAll(){
        String listPatients = getAllString();

        JOptionPane.showMessageDialog(null, listPatients);
    }

    public static String getAllString(){
        PatientModel patientModel = new PatientModel();
        String listPatients = "Patients List: \n";

        for (Object iterator: patientModel.findAll()){
            Patient patient = (Patient) iterator;

            listPatients += patient.toString() + "\n";
        }
        return listPatients;
    }

    public static void create(){
        // Use model
        PatientModel patientModel = new PatientModel();

        // Info user
        String name = JOptionPane.showInputDialog("Insert name: ");
        String last_name = JOptionPane.showInputDialog("Insert lastname: ");
        String date_birth = JOptionPane.showInputDialog("Insert your date of birth");
        String identification_document = JOptionPane.showInputDialog("Insert your identification document");

        // Create Patient
        Patient patient = new Patient();

        patient.setName(name);
        patient.setLast_name(last_name);
        patient.setDate_birth(date_birth);
        patient.setIdentification_document(identification_document);

        // Call method to insertion
        patient = (Patient) patientModel.insert(patient);

        JOptionPane.showMessageDialog(null, patient.toString());
    }

    public static void update(){
        // Use Model
        PatientModel patientModel = new PatientModel();

        // Get specialties list
        List<Object> listPatients = patientModel.findAll();

        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an option",
                "Patients",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listPatients.toArray(),
                listPatients.getFirst()

        );

        if (option == null){
            JOptionPane.showMessageDialog(null, "Patient not found");
        } else {
            Patient patient = (Patient) option;

            // Input data
            String name = JOptionPane.showInputDialog("Enter new name: ", patient.getName());
            String last_name = JOptionPane.showInputDialog("Enter new lastname: ", patient.getLast_name());
            String date_birth = JOptionPane.showInputDialog("Enter new date of birth: ", patient.getDate_birth());
            String identification_document = JOptionPane.showInputDialog("Enter new document: ", patient.getIdentification_document());

            // Set Data
            patient.setName(name);
            patient.setLast_name(last_name);
            patient.setDate_birth(date_birth);
            patient.setIdentification_document(identification_document);

            // Update data
            patientModel.update(patient);
        }
    }

    public static void delete(){
        // use model
        PatientModel patientModel = new PatientModel();

        // Get specialties list
        List<Object> listPatients = patientModel.findAll();

        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an option",
                "Patients",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listPatients.toArray(),
                listPatients.getFirst()

        );

        if (option == null){
            JOptionPane.showMessageDialog(null, "Patient not found");
        } else {
            Patient patient = (Patient) option;
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want  to delete the patient? \n" + patient.toString());

            if (confirm == 0 ) patientModel.delete(patient);
        }
    }

    public static void getByIdentification_document(){
        // Use model
        PatientModel patientModel = new PatientModel();

        // Get Document to search
        String identification_document = JOptionPane.showInputDialog("Enter document: ");

        Patient patient = patientModel.findByIdentification_document(identification_document);

        if (patient == null){
            JOptionPane.showMessageDialog(null, "Patient not found");
        } else {
            JOptionPane.showMessageDialog(null, patient.toString());
        }
    }
}
