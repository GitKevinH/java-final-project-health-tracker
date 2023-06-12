package org.ctac.java103.controllers;

import org.ctac.java103.models.Exercise;

import java.util.*;

public class ExerciseManager {
    private LinkedList<Exercise> exerciseList;

    public static void main(String[] args) {
        ExerciseManager exerciseManager = new ExerciseManager();
        Exercise exercise1 = new Exercise("Running", 30, 300);
        Exercise exercise2 = new Exercise("Cycling", 45, 400);
        exerciseManager.addExercise(exercise1);
        exerciseManager.addExercise(exercise2);

        exerciseManager.menu();


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

        System.out.println("Exercise added.");
    }

    public void printAllExercises() {
        System.out.println("Exercise List:");
        for (Exercise exercise : exerciseList) {
            printExerciseDetails(exercise);
        }
    }

    public int calculateTotalCaloriesLast24Hours() {
        int totalCalories = 0;
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date twentyFourHoursAgo = calendar.getTime();

        for (Exercise exercise : exerciseList) {
            Date exerciseDate = exercise.getDate();
            if (exerciseDate.after(twentyFourHoursAgo) && exerciseDate.before(currentDate)) {
                totalCalories += exercise.getCalsBurned();
            }
        }

        return totalCalories;
    }

    public int calculateTotalCaloriesLast720Hours() {
        int totalCalories = 0;
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.HOUR_OF_DAY, -720);
        Date sevenTwentyHoursAgo = calendar.getTime();

        for (Exercise exercise : exerciseList) {
            Date exerciseDate = exercise.getDate();
            if (exerciseDate.after(sevenTwentyHoursAgo) && exerciseDate.before(currentDate)) {
                totalCalories += exercise.getCalsBurned();
            }
        }

        return totalCalories;
    }







    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Exercise Manager Menu");
            System.out.println("1. Add An exercise");
            System.out.println("2. Print All Exercises");
            System.out.println("3. Calculate Total Calories from Exercises (last 30 days");
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
                    System.out.println(calculateTotalCaloriesLast24Hours());
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

