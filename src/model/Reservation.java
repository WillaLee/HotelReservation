package model;

import java.util.Calendar;

public class Reservation {

    private Customer customer;
    private IRoom room;
    private Calendar checkInDate;
    private Calendar checkOutDate;

    public Reservation(Customer customer, IRoom room, Calendar checkInDate, Calendar checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation\n" +
                 customer +
                "\nRoom: " + room + " - " + room.getRoomType().name() +
                "\nCheckin Date: " + checkInDate.getTime() +
                "\nCheckout Date: " + checkOutDate.getTime();
    }
}
