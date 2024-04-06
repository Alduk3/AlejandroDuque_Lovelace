package entity;

public class Appointment {
    private int id_appointment;
    private int id_patient;
    private int id_doctor;
    private String appointment_date;
    private String appointment_time;
    private String reason;

    public Appointment() {
    }

    public Appointment(int id_appointment, int id_patient, int id_doctor, String appointment_date, String appointment_time, String reason) {
        this.id_appointment = id_appointment;
        this.id_patient = id_patient;
        this.id_doctor = id_doctor;
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.reason = reason;
    }

    public int getId_appointment() {
        return id_appointment;
    }

    public void setId_appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id_appointment=" + id_appointment +
                ", id_patient=" + id_patient +
                ", id_doctor=" + id_doctor +
                ", appointment_date='" + appointment_date + '\'' +
                ", appointment_time='" + appointment_time + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
