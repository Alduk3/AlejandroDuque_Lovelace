package controller;

import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import model.AppointmentModel;
import model.DoctorModel;
import model.PatientModel;

import javax.swing.*;
import java.util.List;

public class AppointmentController {

    public static void getAll(){
        String listAppointments = getAllString();

        JOptionPane.showMessageDialog(null, listAppointments);
    }
    public static String getAllString(){
        AppointmentModel appointmentModel = new AppointmentModel();
        String listAppointments = "Appointments list \n";

        for (Object iterator: appointmentModel.findAll()){
            Appointment appointment = (Appointment) iterator;

            listAppointments += appointment.toString() + "\n";
        }


        return  listAppointments;
    }

    public static void create(){
        // Use model
        AppointmentModel appointmentModel = new AppointmentModel();
        PatientModel patientModel = new PatientModel();
        DoctorModel doctorModel = new DoctorModel();

        // Info user
        //Get id_patient

        int id_patient = 0;

        List<Object> listPatients = patientModel.findAll();

        Object option_patient = JOptionPane.showInputDialog(
                null,
                "Choose an option",
                "Patients",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listPatients.toArray(),
                listPatients.getFirst()

        );
        if (option_patient == null){
            JOptionPane.showMessageDialog(null, "Patient not found");
        } else {
            Patient patient = (Patient) option_patient;
            id_patient = patient.getId_patient();
        }
        //Get id_doctor
        int id_doctor = 0;

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
            id_doctor = doctor.getId_doctor();
        }

        String appointment_date = JOptionPane.showInputDialog("Enter appointment date: ");
        String appointment_time = JOptionPane.showInputDialog("Enter appointment time: ");
        String reason = JOptionPane.showInputDialog("Enter appointment reason: ");

        // Create appointment

        Appointment appointment = new Appointment();

        appointment.setId_patient(id_patient);
        appointment.setId_doctor(id_doctor);
        appointment.setAppointment_date(appointment_date);
        appointment.setAppointment_time(appointment_time);
        appointment.setReason(reason);

        appointment = (Appointment) appointmentModel.insert(appointment);

        JOptionPane.showMessageDialog(null, appointment.toString());

    }

    public static void update(){
        // use model
        AppointmentModel appointmentModel = new AppointmentModel();
        DoctorModel doctorModel = new DoctorModel();

        // Get appointment list
        List<Object> listAppointments = appointmentModel.findAll();

        Object option_appointment = JOptionPane.showInputDialog(
                null,
                "Choose an option",
                "Appointments",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listAppointments.toArray(),
                listAppointments.getFirst()

        );

        if (option_appointment == null){
            JOptionPane.showMessageDialog(null, "Patient not found");
        } else {

            Appointment appointment = (Appointment) option_appointment;

            // Input data

            //Get id_doctor
            int id_doctor = 0;

            List<Object> listDoctors = doctorModel.findAll();

            Object option2 = JOptionPane.showInputDialog(
                    null,
                    "Choose a new Doctor",
                    "Doctors",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    listDoctors.toArray(),
                    listDoctors.getFirst()

            );

            if (option2 == null) {
                JOptionPane.showMessageDialog(null, "Doctor not found");
            } else {
                Doctor doctor = (Doctor) option2;
                id_doctor = doctor.getId_doctor();
            }

            String appointment_date = JOptionPane.showInputDialog("Enter new appointment date: ", appointment.getAppointment_date());
            String appointment_time = JOptionPane.showInputDialog("Enter new appointment time: ", appointment.getAppointment_time());
            String reason = JOptionPane.showInputDialog("Enter new appointment reason: ", appointment.getReason());

            // Set data

            appointment.setId_doctor(id_doctor);
            appointment.setAppointment_date(appointment_date);
            appointment.setAppointment_time(appointment_time);
            appointment.setReason(reason);

            appointmentModel.update(appointment);

        }

    }

    public static void delete(){
        // Use model
        AppointmentModel appointmentModel = new AppointmentModel();

        // Get appointment list
        List<Object> listAppointments = appointmentModel.findAll();

        Object option_appointment = JOptionPane.showInputDialog(
                null,
                "Choose an option",
                "Appointments",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listAppointments.toArray(),
                listAppointments.getFirst()

        );

        if (option_appointment == null){
            JOptionPane.showMessageDialog(null, "Patient not found");
        } else {
            Appointment appointment = (Appointment) option_appointment;

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want  to delete the appointment? \n" + appointment.toString());

            if (confirm == 0 ) appointmentModel.delete(appointment);

        }
    }

    public static void getByDate(){
        // Use model
        AppointmentModel appointmentModel = new AppointmentModel();

        // Get Date to search
        String appointment_date = JOptionPane.showInputDialog("Enter appointment date: ");

        // Create String lis to appointment
        String appointmentList = "Appointment on " + appointment_date + ":\n";

        // isEmpty
        boolean isEmpty = true;

        for (Appointment iterator : appointmentModel.findByDate(appointment_date)){
            isEmpty = false;
            appointmentList += iterator.toString();
        }

        if (!isEmpty) {
            JOptionPane.showMessageDialog(null, appointmentList);
        } else {
            JOptionPane.showMessageDialog(null, "Appointments not found to "+appointment_date);
        }


    }
}
