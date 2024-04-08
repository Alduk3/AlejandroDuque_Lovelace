package entity;

public class Flight {
    private int id_flight;
    private String destiny;
    private String departure_date;
    private String departure_time;
    private int id_airplane;

    public Flight() {
    }

    public Flight(int id_flight, String destiny, String departure_date, String departure_time, int id_airplane) {
        this.id_flight = id_flight;
        this.destiny = destiny;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.id_airplane = id_airplane;
    }

    public int getId_flight() {
        return id_flight;
    }

    public void setId_flight(int id_flight) {
        this.id_flight = id_flight;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public int getId_airplane() {
        return id_airplane;
    }

    public void setId_airplane(int id_airplane) {
        this.id_airplane = id_airplane;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id_flight=" + id_flight +
                ", destiny='" + destiny + '\'' +
                ", departure_date='" + departure_date + '\'' +
                ", departure_time='" + departure_time + '\'' +
                ", id_airplane=" + id_airplane +
                '}';
    }
}
