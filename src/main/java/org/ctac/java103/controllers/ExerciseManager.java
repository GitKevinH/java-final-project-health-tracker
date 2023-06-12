package org.ctac.java103.controllers;

import org.ctac.java103.models.Exercise;

import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class ExerciseManager {
    private LinkedList<Exercise> exerciseList;

    public static void main(String[] args) {
        ExerciseManager exerciseManager = new ExerciseManager();
        Exercise exercise1 = new Exercise("Running", 30, 300);
        Exercise exercise2 = new Exercise("Cycling", 45, 400);
        exerciseManager.addExercise(exercise1);
        exerciseManager.addExercise(exercise2);

        // Calculate the total calories burned
        int totalCalories = exerciseManager.calculateTotalCalories();
        System.out.println("Total Calories Burned: " + totalCalories);

        exerciseManager.createExercise();
    }

    public ExerciseManager() {
        exerciseList = new LinkedList<>();
    }

    public void addExercise(Exercise exercise) {
        exerciseList.add(exercise);
    }

    public void removeExercise(Exercise exercise) {
        exerciseList.remove(exercise);
    }

    public LinkedList<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void printExerciseDetails(Exercise exercise) {
        System.out.println("Exercise Details:");
        System.out.println("Name: " + exercise.getName());
        System.out.println("Duration: " + exercise.getDuration() + " minutes");
        System.out.println("Calories Burned: " + exercise.getCalsBurned());
        System.out.println("Date: " + exercise.getDate());
        System.out.println();
    }

    public void createExercise() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter exercise name: ");
        String name = scanner.nextLine();

        System.out.print("Enter exercise duration (in minutes): ");
        int duration = scanner.nextInt();

        System.out.print("Enter calories burned: ");
        int calsBurned = scanner.nextInt();


        Exercise exercise = new Exercise(name, duration, calsBurned);
        exerciseList.add(exercise);

        System.out.println("Exercise created successfully.");
    }

    public void printAllExercises() {
        System.out.println("Exercise List:");
        for (Exercise exercise : exerciseList) {
            printExerciseDetails(exercise);
        }
    }

    public int calculateTotalCalories() {
        int totalCalories = 0;
        for (Exercise exercise : exerciseList) {
            totalCalories += exercise.getCalsBurned();
        }
        return totalCalories;
    }

    public int calculateTotalCaloriesForDay(Date day) {
        int totalCalories = 0;

        for (Exercise exercise : exerciseList) {
            if (exercise.getDate().equals(day)) {
                totalCalories += exercise.getCalsBurned();
            }
        }

        return totalCalories;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Exercise Manager Menu");
            System.out.println("1. Add an exercise");
            System.out.println("2. Print All Exercises");
            System.out.println("3. Calculate Total Calories from Exercises");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createExercise();
                    break;
                case 2:
                    printAllExercises();
                    break;
                case 3:
                    System.out.println("Total calories logged (30 days): " +calculateTotalCalories());
                    break;
                case 4:
                    System.out.println("Exiting.. ");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }



}

