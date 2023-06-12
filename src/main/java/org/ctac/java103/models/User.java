package org.ctac.java103.models;

import org.ctac.java103.controllers.CalorieIntakeManager;
import org.ctac.java103.controllers.ExerciseManager;
import org.ctac.java103.controllers.SleepManager;

import java.util.Scanner;

public class User {
    private String username;
    private SleepManager sleepManager;
    private ExerciseManager exerciseManager;
    private CalorieIntakeManager calorieIntakeManager;

    public User(String username) {
        this.username = username;
        this.sleepManager = new SleepManager();
        this.exerciseManager = new ExerciseManager();
        this.calorieIntakeManager = new CalorieIntakeManager();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SleepManager getSleepManager() {
        return sleepManager;
    }

    public void setSleepManager(SleepManager sleepManager) {
        this.sleepManager = sleepManager;
    }

    public ExerciseManager getExerciseManager() {
        return exerciseManager;
    }

    public void setExerciseManager(ExerciseManager exerciseManager) {
        this.exerciseManager = exerciseManager;
    }

    public CalorieIntakeManager getCalorieIntakeManager() {
        return calorieIntakeManager;
    }

    public void setCalorieIntakeManager(CalorieIntakeManager calorieIntakeManager) {
        this.calorieIntakeManager = calorieIntakeManager;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Select an option:");
            System.out.println("1. Manage Sleep");
            System.out.println("2. Manage Calorie Intake");
            System.out.println("3. Manage Exercises");
            System.out.println("4. Health Data Analysis");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    sleepManager.menu();
                    break;
                case 2:
                    calorieIntakeManager.menu();
                    break;
                case 3:
                    exerciseManager.menu();
                    break;
                case 4:
                    healthMenu();
                    break;

                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public void healthMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Select an option:");
            System.out.println("1. Get Daily Caloric Balance");
            System.out.println("2. Get Average Sleep of 7 Days");
            System.out.println("3. Get Exercise Log");
            System.out.println("4. Health Summary");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println();
                    System.out.println("Calories Burned: " + exerciseManager.calculateTotalCaloriesLast24Hours());
                    System.out.println("Calories Consumed: " + calorieIntakeManager.calculateTotalCaloriesLast24Hours());
                    System.out.println("Difference in Calories burned over consumed: " + (exerciseManager.calculateTotalCaloriesLast24Hours()-calorieIntakeManager.calculateTotalCaloriesLast24Hours()));
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Your Average Sleep Over 7 Days Was: " + sleepManager.calculateAverageSleepPerNumDays(7));
                    break;
                case 3:
                    System.out.println();
                    exerciseManager.printAllExercises();
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    System.out.println("Health Summary:");
                    System.out.println("Average sleep over 30 days: " + sleepManager.calculateAverageSleepPerNumDays(30) );
                    System.out.println("Calories consumed over 30 days: " + calorieIntakeManager.calculateTotalCaloriesLast720Hours());
                    System.out.println("Calories burned over 30 days: " + exerciseManager.calculateTotalCaloriesLast720Hours());
                    System.out.println("Difference in Calories burned over consumed last 30 days: " + (exerciseManager.calculateTotalCaloriesLast720Hours()-calorieIntakeManager.calculateTotalCaloriesLast720Hours()));
                    System.out.println();
                    break;

                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

}

