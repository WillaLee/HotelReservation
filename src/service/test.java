package service;

import model.IRoom;
import model.Reservation;
import model.Room;
import model.RoomType;

import java.util.Calendar;

public class test {
    public static void main(String[] args) {
        CustomerService manager = new CustomerService();
        manager.addCustomer("willalee@gmail.com", "willa", "Li");
        manager.addCustomer("logan@gmail.com","logan","li");
        System.out.println(manager.getCustomer("willalee@gmail.com"));
        System.out.println(manager.getAllCustomers());
        ReservationService doormanager = new ReservationService();

        IRoom room1 = new Room("101", 135.0, RoomType.SINGLE);
        IRoom room2 = new Room("102", 160.0, RoomType.DOUBLE);
        doormanager.addRoom(room1);
        doormanager.addRoom(room2);
        System.out.println(doormanager.getARoom("102"));

        Calendar willaCheckInDate = Calendar.getInstance();
        Calendar willaCheckOutDate = Calendar.getInstance();
        Calendar loganCheckInDate = Calendar.getInstance();
        Calendar loganCheckOutDate = Calendar.getInstance();
        willaCheckInDate.set(2023, Calendar.NOVEMBER, 16);
        willaCheckOutDate.set(2023, Calendar.NOVEMBER, 20);
        loganCheckInDate.set(2023, Calendar.NOVEMBER, 20);
        loganCheckOutDate.set(2023, Calendar.NOVEMBER, 22);
        doormanager.reserveARoom(manager.getCustomer("willalee@gmail.com"), room1, willaCheckInDate, willaCheckOutDate);
        System.out.println(doormanager.findRooms(loganCheckInDate, loganCheckOutDate));
        System.out.println(doormanager.getCustomerReservation(manager.getCustomer("willalee@gmail.com")));
        doormanager.printAllReservation();



    }
}
