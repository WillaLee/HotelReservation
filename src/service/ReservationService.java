package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static Map<Customer, List<Reservation>> customerReservation = new HashMap<>();
    private static Map<String, IRoom> rooms = new HashMap<>();

    public static void addRoom(IRoom room){
        rooms.put(room.getRoomNumber(), room);
    }

    public static IRoom getARoom(String roomId){
        return rooms.get(roomId);
    }

    public static Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Calendar checkInDate, Calendar checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        if (customerReservation.containsKey(customer)){
            customerReservation.get(customer).add(reservation);
        }
        else {
            List<Reservation> reservations = new ArrayList<>();
            reservations.add(reservation);
            customerReservation.put(customer, reservations);
        }
        return reservation;
    }

    public static List<IRoom> findRooms(Calendar checkInDate, Calendar checkOutDate){
        Set<IRoom> occupiedRooms = new HashSet<>();
        List<IRoom> availableRooms = new ArrayList<>();
        for (List<Reservation> reservations : customerReservation.values()){
            for (Reservation reservation : reservations){
                if (((reservation.getCheckInDate().compareTo(checkInDate) > 0) && (reservation.getCheckInDate().compareTo(checkOutDate) < 0))
                || ((reservation.getCheckOutDate().compareTo(checkInDate) > 0) && (reservation.getCheckOutDate().compareTo(checkOutDate) < 0))){
                    occupiedRooms.add(reservation.getRoom());
                }
            }
        }
        for (IRoom room : rooms.values()){
            if (occupiedRooms.contains(room)){
                continue;
            }else {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public static List<Reservation> getCustomerReservation(Customer customer){
        return customerReservation.get(customer);
    }

    public static void printAllReservation(){
        for (Customer customer : customerReservation.keySet()){
            System.out.println(customer);
            for (Reservation reservation : customerReservation.get(customer)){
                System.out.println(reservation);
            }
        }
    }
}
