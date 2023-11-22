package menu;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;
        while (true) {
            System.out.println("input:");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                break;
            } else {
                System.out.println("Please input a number (1\\2\\3\\4\\5)!");
                scanner.next();
            }
        }

        System.out.println(choice);
    }
}
