package entity;

public class Passenger {
    private int id_passenger;
    private String name;
    private String last_name;
    private String identification_document;

    public Passenger() {
    }

    public Passenger(int id_passenger, String name, String last_name, String identification_document) {
        this.id_passenger = id_passenger;
        this.name = name;
        this.last_name = last_name;
        this.identification_document = identification_document;
    }

    public int getId_passenger() {
        return id_passenger;
    }

    public void setId_passenger(int id_passenger) {
        this.id_passenger = id_passenger;
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

    public String getIdentification_document() {
        return identification_document;
    }

    public void setIdentification_document(String identification_document) {
        this.identification_document = identification_document;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id_passenger=" + id_passenger +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", identification_document='" + identification_document + '\'' +
                '}';
    }
}
