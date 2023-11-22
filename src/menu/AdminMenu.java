package menu;

import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void adminMenu(){
        while (true){
            System.out.println("Welcome, admin! Please select an affair:");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Back to Main Menu");
            int choice = getAChoice(5);
            switch (choice){
                case 1:
                    System.out.println(AdminResource.getAllCustomers());
                    break;
                case 2:
                    System.out.println(AdminResource.getAllRooms());
                    break;
                case 3:
                    AdminResource.displayAllReservations();
                    break;
                case 4:
                    List<IRoom> roomsToAdd = inputRoomToAdd();
                    AdminResource.addRoom(roomsToAdd);
                    break;
                case 5:
                    return;
            }
        }
    }

    private static List<IRoom> inputRoomToAdd(){
        int countNewRoom = 0;
        List<IRoom> roomsToAdd = new ArrayList<>();
        while (true){
            System.out.println("Please enter room number:");
            String roomNumber = scanner.nextLine();
            double roomPrice = inputRoomPrice();
            RoomType roomType = inputRoomType();
            IRoom newRoom = new Room(roomNumber, roomPrice, roomType);
            roomsToAdd.add(newRoom);
            countNewRoom += 1;
            System.out.printf("You have add %d rooms. Would you like to continue adding rooms? (Y/N)%n", countNewRoom);
            String choice;
            while (true){
                choice = scanner.nextLine().toUpperCase();
                System.out.println(choice);
                if (choice.equals("N") || choice.equals("Y")){
                    break;
                }else {
                    System.out.println("Please input Y or N!");
                }
            }
            switch (choice){
                case "Y":
                    break;
                case "N":
                    return roomsToAdd;
            }
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

    private static double inputRoomPrice(){
        while (true){
            System.out.println("Please enter room price:");
            try {
                double roomPrice = Double.parseDouble(scanner.nextLine());
                return roomPrice;
            }catch (IllegalArgumentException e){
                System.out.println("Invalid room price!");
            }
        }
    }

    private static RoomType inputRoomType(){
        while (true){
            System.out.println("Please enter room type:");
            try {
                RoomType roomType = RoomType.valueOf(scanner.nextLine().toUpperCase());
                return roomType;
            }catch (IllegalArgumentException e){
                System.out.println("Invalid room type!");
            }
        }
    }
}
