package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Calendar;
import java.util.List;

public class HotelResource {

    public static Customer getCustomer(String customerEmail){
        return CustomerService.getCustomer(customerEmail);
    }

    public static void createACustomer(String email, String firstName, String lastName){
        CustomerService.addCustomer(email, firstName, lastName);
    }

    public static IRoom getRoom(String roomNumber){
        return ReservationService.getARoom(roomNumber);
    }

    public static Reservation bookARoom(Customer customer, IRoom room, Calendar checkInDate, Calendar checkOutDate){
        return ReservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public static List<Reservation> getCustomerReservation(String customerEmail){
        return ReservationService.getCustomerReservation(CustomerService.getCustomer(customerEmail));
    }

    public static List<IRoom> findARoom(Calendar checkInDate, Calendar checkOutDate){
        return ReservationService.findRooms(checkInDate,checkOutDate);
    }

}
