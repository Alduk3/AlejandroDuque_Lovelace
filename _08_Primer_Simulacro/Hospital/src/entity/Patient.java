package entity;

public class Patient {
    private int id_patient;
    private String name;
    private String last_name;
    private String date_birth;
    private String identification_document;

    public Patient() {
    }

    public Patient(int id_patient, String name, String last_name, String date_birth, String identification_document) {
        this.id_patient = id_patient;
        this.name = name;
        this.last_name = last_name;
        this.date_birth = date_birth;
        this.identification_document = identification_document;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
    }

    public String getIdentification_document() {
        return identification_document;
    }

    public void setIdentification_document(String identification_document) {
        this.identification_document = identification_document;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id_patient=" + id_patient +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", date_birth='" + date_birth + '\'' +
                ", identification_document='" + identification_document + '\'' +
                '}';
    }
}
