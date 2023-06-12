package org.ctac.java103.view;

import org.ctac.java103.controllers.UserManagement;
import org.ctac.java103.controllers.UserManagementFileIO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Could not get the darn file reader to work in time

        String fileName = "users.txt";

        /* UserManagement userManagement = UserManagementFileIO.loadUserManagement(fileName);
        if (userManagement != null) {

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Exercise Manager Menu");
                System.out.println("1. Login");
                System.out.println("2. Create New User");
                System.out.println("3. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        userManagement.findUserByUsername();
                        break;
                    case 2:
                        userManagement.addNewUser();
                        System.out.println("You can now login at the main menu.");
                        break;
                    case 3:
                        UserManagementFileIO.saveUserManagement(userManagement, fileName);
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

                System.out.println();

            }

        }*/

        UserManagement userManagement = new UserManagement();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Exercise Manager Menu - hint create a new user first");
            System.out.println("1. Login");
            System.out.println("2. Create New User");
            System.out.println("3. Exit");

            try {
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        userManagement.findUserByUsername();
                        break;
                    case 2:
                        userManagement.addNewUser();
                        System.out.println("You can now login at the main menu.");
                        break;
                    case 3:
                        UserManagementFileIO.saveUserManagement(userManagement, fileName);
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }

            System.out.println();

        }




    }
}