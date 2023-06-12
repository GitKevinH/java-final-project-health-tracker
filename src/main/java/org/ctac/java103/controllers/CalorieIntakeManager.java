package org.ctac.java103.controllers;

import org.ctac.java103.models.CalorieIntake;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class CalorieIntakeManager {
    private LinkedList<CalorieIntake> intakeList;

    public static void main(String[] args) {
        CalorieIntakeManager calorieIntakeManager = new CalorieIntakeManager();

        CalorieIntake intake1 = new CalorieIntake("Apple", 100);
        CalorieIntake intake2 = new CalorieIntake("Pizza", 800);
        CalorieIntake intake3 = new CalorieIntake("Salad", 200);

        calorieIntakeManager.addCalorieIntake(intake1);
        calorieIntakeManager.addCalorieIntake(intake2);
        calorieIntakeManager.addCalorieIntake(intake3);

        calorieIntakeManager.menu();
    }

    public CalorieIntakeManager() {
        intakeList = new LinkedList<>();
    }

    public void addCalorieIntake(CalorieIntake calorieIntake) {
        intakeList.add(calorieIntake);
    }

    public void removeCalorieIntake(CalorieIntake calorieIntake) {
        intakeList.remove(calorieIntake);
    }

    public LinkedList<CalorieIntake> getIntakeList() {
        return intakeList;
    }

    public int getTotalCalories() {
        int totalCalories = 0;
        for (CalorieIntake intake : intakeList) {
            totalCalories += intake.getCalories();
        }
        return totalCalories;
    }

    public void createAndAddCalorieIntake() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the food: ");
        String food = scanner.nextLine();

        int calories = 0;
        boolean validCalories = false;
        while (!validCalories) {
            try {
                System.out.println("Enter the calories: ");
                calories = scanner.nextInt();
                validCalories = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid calorie value.");
                scanner.nextLine(); // Clear the input buffer
            }
        }

        CalorieIntake calorieIntake = new CalorieIntake(food, calories);
        intakeList.add(calorieIntake);

        System.out.println("Added Calorie Intake.");
    }

    public void printCalorieIntakeList() {
        System.out.println("Calorie Intake List:");

        if (intakeList.isEmpty()) {
            System.out.println("No items in the list.");
        } else {
            for (CalorieIntake intake : intakeList) {
                System.out.println("Food: " + intake.getFood());
                System.out.println("Calories: " + intake.getCalories());
                System.out.println("Date: " + intake.getDate());
                System.out.println("--------------------");
            }
        }
    }

    public int calculateTotalCaloriesLast24Hours() {
        int totalCalories = 0;
        Date currentDate = new Date();

        long cutoffTimeInMillis = currentDate.getTime() - (24 * 60 * 60 * 1000);

        for (CalorieIntake intake : intakeList) {
            if (intake.getDate().getTime() >= cutoffTimeInMillis) {
                totalCalories += intake.getCalories();
            }
        }

        return totalCalories;
    }

    public int calculateTotalCaloriesLast720Hours() {
        int totalCalories = 0;
        Date currentDate = new Date();

        long cutoffTimeInMillis = currentDate.getTime() - (720L * 60L * 60L * 1000L);

        for (CalorieIntake intake : intakeList) {
            if (intake.getDate().getTime() >= cutoffTimeInMillis) {
                totalCalories += intake.getCalories();
            }
        }

        return totalCalories;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Add Calorie Intake");
            System.out.println("2. Print Calorie Intake List");
            System.out.println("3. Calculate Total Calories Last 24 Hours");
            System.out.println("4. Calculate Total Calories Last 30 Days");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        createAndAddCalorieIntake();
                        break;
                    case 2:
                        printCalorieIntakeList();
                        break;
                    case 3:
                        int totalCaloriesLast24Hours = calculateTotalCaloriesLast24Hours();
                        System.out.println("Total calories consumed in the last 24 hours: " + totalCaloriesLast24Hours);
                        break;
                    case 4:
                        int totalCaloriesLast720Hours = calculateTotalCaloriesLast720Hours();
                        System.out.println("Total calories consumed in the last 30 days: " + totalCaloriesLast720Hours);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.nextLine(); // Clear the input buffer
                choice = -1; // Set choice to an invalid value to repeat the loop
            }
        } while (choice != 0);
    }
}
