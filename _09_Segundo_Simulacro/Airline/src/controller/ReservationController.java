package controller;

import entity.Flight;
import entity.Passenger;
import entity.Reservation;
import model.FlightModel;
import model.ReservationModel;

import javax.swing.*;
import java.util.List;

public class ReservationController {
    public static void getAll(){
        // Use model
        ReservationModel reservationModel = new ReservationModel();

        // Create string list to reservations
        String reservationList = "Reservation list: \n";

        for (Object iterator : reservationModel.findAll()){
            // Convert iterator to reservation
            Reservation reservation = (Reservation) iterator;
            reservationList += reservation.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, reservationList);
    }

    public static void create(){
        // Use model
        ReservationModel reservationModel = new ReservationModel();

        // Get info from user
        Flight flight = FlightController.getFlight();

        if (getAvailability(flight)){

            Passenger passenger = PassengerController.getPassenger();
            String reservation_date = JOptionPane.showInputDialog("Enter reservation_date");
            String seat = "";

            // Seat availability
            boolean seatAvailability = true;

            while (seatAvailability){

                seat = JOptionPane.showInputDialog("Enter your seat: ");

                if (getAvailableSeat(flight.getId_flight(), seat)){
                    seatAvailability = false;
                } else {
                    JOptionPane.showMessageDialog(null, "Seat not available :( ");
                }
            }

            // Create Reservation
            Reservation reservation = new Reservation();

            // Set info to reservation
            reservation.setId_passenger(passenger.getId_passenger());
            reservation.setId_flight(flight.getId_flight());
            reservation.setReservation_date(reservation_date);
            reservation.setSeat(seat);

            // Call method to insert and get the id
            reservation = (Reservation) reservationModel.insert(reservation);

            JOptionPane.showMessageDialog(null, reservation);

        } else {
            JOptionPane.showMessageDialog(null, "The flight is full :(");
        }
    }

    public static void update(){
        // Use model
        ReservationModel reservationModel = new ReservationModel();

        // Select Reservation
        Reservation reservation = getReservation();

        // Get status capacity availability


        // Get info updated from user
        Flight flight = FlightController.getFlight();

        if (getAvailability(flight)){

            Passenger passenger = PassengerController.getPassenger();
            String reservation_date = JOptionPane.showInputDialog("Enter new reservation date", reservation.getReservation_date());
            String seat = "";

            // Seat availability
            boolean seatAvailability = true;

            while (seatAvailability){

                seat = JOptionPane.showInputDialog("Enter your new seat: ", reservation.getSeat());

                if (getAvailableSeat(flight.getId_flight(), seat)){
                    seatAvailability = false;
                } else {
                    JOptionPane.showMessageDialog(null, "Seat not available :( ");
                }
            }

            // Set new info
            reservation.setId_passenger(passenger.getId_passenger());
            reservation.setId_flight(flight.getId_flight());
            reservation.setReservation_date(reservation_date);
            reservation.setSeat(seat);

            // Call method to update
            reservationModel.update(reservation);
        } else {
            JOptionPane.showMessageDialog(null, "The flight is full :(");
        }


    }

    public static void delete(){
        // Use model
        ReservationModel reservationModel = new ReservationModel();

        // Select reservation
        Reservation reservation = getReservation();

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete \n" + reservation.toString());

        if (confirm == 0) reservationModel.delete(reservation);
    }

    public static Reservation getReservation(){
        // Use model
        ReservationModel reservationModel = new ReservationModel();

        // Create list to search
        List<Object> reservationList = reservationModel.findAll();

        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an reservation",
                "Reservations",
                JOptionPane.QUESTION_MESSAGE,
                null,
                reservationList.toArray(),
                reservationList.getFirst()
        );

        return (Reservation) option;
    }

    public static void getByFlight(){
        // Use model
        ReservationModel reservationModel = new ReservationModel();


        // Get flight
        Flight flight = FlightController.getFlight();

        // Create String list to reservations
        String reservationList = "Reservations to : " + flight.toString() + "\n";

        for (Reservation iterator : reservationModel.findByFlight(flight.getId_flight())){
            reservationList += iterator.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, reservationList);
    }

    public static boolean getAvailability(Flight flight){
        // Use model
        ReservationModel reservationModel = new ReservationModel();

        boolean availability = false;

        // Get airplane capacity
        int airplaneCapacity = AirplaneController.getCapacity(flight.getId_airplane());

        // Get number of booked flights
        int numberBookedFlights = reservationModel.findNumberBookedFlights(flight.getId_flight());

        if (numberBookedFlights < airplaneCapacity) {
            availability = true;
        }

        return availability;
    }

    public static boolean getAvailableSeat(int id_flight, String seat){
        // Use model
        ReservationModel reservationModel = new ReservationModel();

        // Seat status
        boolean isAvailability = true;

        if (reservationModel.findNumberSimilarSeat(id_flight, seat) > 0) {
            isAvailability = false;
        }

        return isAvailability;
    }
}
