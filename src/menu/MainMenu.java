package menu;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainMenu {
    static Scanner scanner = new Scanner(System.in);
    public static void mainMenu() {
        while (true){
            System.out.println("Welcome to Hotel Babylon! Please select a service:");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");

            int choice = getAChoice(5);

            switch (choice){
                case 1:
                    findAndReserve();
                    break;
                case 2:
                    seeMyReservations();
                    break;
                case 3:
                    createAccount();
                    break;
                case 4:
                    AdminMenu.adminMenu();
                    break;
                case 5:
                    return;
            }
        }
    }

    private static void findAndReserve(){
        Customer customer = getACustomer();
        if (customer == null){
            return;
        }
        Calendar checkInDate = getADate("check in date");
        Calendar checkOutDate = getADate("check out date");
        List<IRoom> availableRoom = HotelResource.findARoom(checkInDate, checkOutDate);
        if (availableRoom.isEmpty()){
            checkInDate.add(Calendar.DAY_OF_MONTH, 7);
            checkOutDate.add(Calendar.DAY_OF_MONTH, 7);
            availableRoom = HotelResource.findARoom(checkInDate, checkOutDate);
            if (availableRoom.isEmpty()){
                System.out.println("Sorry, there is no room for you.");
                return;
            }
            System.out.printf("Sorry, there is no room for you, " +
                            "but we have search recommend room between %s and %s for you.%n",
                    checkInDate.getTime(), checkOutDate.getTime());
        }
        while (true){
            System.out.println("Available room: " + availableRoom);
            System.out.println("Please select a room number: ");
            String roomNumber = scanner.nextLine();
            IRoom roomToReserve = HotelResource.getRoom(roomNumber);
            if (availableRoom.contains(roomToReserve)){
                Reservation reservation = HotelResource.bookARoom(customer, roomToReserve, checkInDate, checkOutDate);
                System.out.println("Thanks for your reservation!" +
                        " This is your reservation information: " + reservation);
                return;
            }else {
                System.out.println("Please enter a valid room number!");
            }
        }
    }

    private static void seeMyReservations(){
        Customer customer = getACustomer();
        if (customer == null){
            return;
        }
        System.out.println(HotelResource.getCustomerReservation(customer.getEmail()));;
    }

    private static void createAccount(){
        System.out.println("Please enter your first name:");
        String firstName = scanner.nextLine();
        System.out.println("Please enter your last name:");
        String lastName = scanner.nextLine();
        while (true){
            try {
                System.out.println("Please enter your email:");
                String email = scanner.nextLine();
                HotelResource.createACustomer(email, firstName, lastName);
                System.out.println("You have successfully create an account! Here is your account information: "
                        + HotelResource.getCustomer(email));
                System.out.println("You can enjoy your membership now!");
                return;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
    private static Calendar getADate(String dateType){
        while (true){
            try {
                System.out.printf("Please enter your %s (YYYY-MM-DD):%n", dateType);
                String dateString = scanner.nextLine();
                Calendar date = parseDate(dateString);
                return date;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static Calendar parseDate(String dateString){
        String[] dateList = dateString.split("-");

        if (dateList.length != 3){
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.");
        }

        int year = Integer.parseInt(dateList[0]);
        int month = Integer.parseInt(dateList[1]);
        int day = Integer.parseInt(dateList[2]);
        Calendar date = Calendar.getInstance();
        date.set(year, month, day);

        return date;
    }

    private static boolean isEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (pattern.matcher(email).matches()){
            return true;
        }
        else {
            return false;
        }
    }

    private static int getAChoice(int validChoices){
        while (true){
            String choiceString = scanner.nextLine();
            try {
                int choice = Integer.parseInt(choiceString);
                if (choice >= 1 & choice <= validChoices){
                    return choice;
                }else {
                    System.out.println("Invalid choice!");
                }
            }catch (NumberFormatException e){
                System.out.println("Please enter a number!");
            }
        }
    }

    private static Customer getACustomer(){
        Customer customer = null;
        while (true){
            System.out.println("Please enter your account (email):");
            String email = scanner.nextLine();
            if (isEmail(email)){
                customer = HotelResource.getCustomer(email);
                if (customer != null){
                    return customer;
                }else {
                    System.out.println("Customer not found! Please choose:");
                    System.out.println("1. enter a correct account number");
                    System.out.println("2. create a customer account");
                    int choice = getAChoice(2);
                    switch (choice){
                        case 1:
                            break;
                        case 2:
                            System.out.println("choose 2");
                            return null;
                    }
                }
            }else {
                System.out.println("Please enter a valid email!");
            }
        }
    }
}
