package entity;

public class Reservation {
    private int id_reservation;
    private int id_passenger;
    private int id_flight;
    private String reservation_date;
    private String seat;

    public Reservation() {
    }

    public Reservation(int id_reservation, int id_passenger, int id_flight, String reservation_date, String seat) {
        this.id_reservation = id_reservation;
        this.id_passenger = id_passenger;
        this.id_flight = id_flight;
        this.reservation_date = reservation_date;
        this.seat = seat;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_passenger() {
        return id_passenger;
    }

    public void setId_passenger(int id_passenger) {
        this.id_passenger = id_passenger;
    }

    public int getId_flight() {
        return id_flight;
    }

    public void setId_flight(int id_flight) {
        this.id_flight = id_flight;
    }

    public String getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(String reservation_date) {
        this.reservation_date = reservation_date;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id_reservation=" + id_reservation +
                ", id_passenger=" + id_passenger +
                ", id_flight=" + id_flight +
                ", reservation_date='" + reservation_date + '\'' +
                ", seat='" + seat + '\'' +
                '}';
    }
}
