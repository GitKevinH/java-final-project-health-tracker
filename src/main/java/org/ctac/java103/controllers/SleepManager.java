package org.ctac.java103.controllers;

import org.ctac.java103.models.Sleep;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class SleepManager {

    public static void main(String[] args) {
    SleepManager sleepManager = new SleepManager();
    sleepManager.menu();



    }
    private LinkedList<Sleep> sleepList;
    private static final DateFormat timeFormat = new SimpleDateFormat("HH:mm");

    //Constructor
    public SleepManager() {
        sleepList = new LinkedList<>();
    }

    //Core Functions

    //Create sleep object and add sleep time
    public void addSleepTime(Date sleepTime) {
        Sleep sleep = new Sleep();
        sleep.setSleepTime(sleepTime);
        sleepList.add(sleep);
    }

    //Grabs the last sleeptime/object and adds the wake time to it
    /*public void addWakeTime(Date wakeTime) {
        if (!sleepList.isEmpty()) {
            Sleep currentSleep = sleepList.getLast();
            currentSleep.setWakeTime(wakeTime);
        } else {
            System.out.println("You need to have a previous sleep time to add a wake time." +
                    " Please create a Sleep time first.");
        }
    }*/

    public void addWakeTime(Date wakeTime) {
        if (!sleepList.isEmpty()) {
            Sleep currentSleep = sleepList.getLast();

            // Check if the wake time is before the sleep time (next day)
            if (wakeTime.before(currentSleep.getSleepTime())) {
                // Increment the wake time by 24 hours
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(wakeTime);
                calendar.add(Calendar.HOUR_OF_DAY, 24);
                wakeTime = calendar.getTime();
            }

            currentSleep.setWakeTime(wakeTime);
        } else {
            System.out.println("Please create a Sleep time first.");
        }
    }

    public double calculateAverageSleepPerNumDays(int days) {
        double totalSleepTime = 0;
        int sleepCount = 0;

        for (Sleep sleep : sleepList) {
            if (sleep.hasValidSleepAndWakeTime()) {
                long sleepDuration = sleep.getWakeTime().getTime() - sleep.getSleepTime().getTime();
                totalSleepTime += sleepDuration;
                sleepCount++;
            }
        }

        if (sleepCount > 0) {
            double averageSleepTime = totalSleepTime / sleepCount;
            return averageSleepTime / (1000 * 60 * 60 * 24 * days);
        } else {
            return 0;
        }
    }


    //UI

    //Sleep time prompt
    public void promptAddSleepTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter sleep time (HH:mm): ");
        String sleepTimeStr = scanner.nextLine();

        try {
            LocalTime sleepTime = LocalTime.parse(sleepTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
            LocalDate currentDate = LocalDate.now();
            LocalDateTime sleepDateTime = LocalDateTime.of(currentDate, sleepTime);

            addSleepTime(Date.from(sleepDateTime.atZone(ZoneId.systemDefault()).toInstant()));
            System.out.println("Added sleep time.");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please try again.");
        }
    }

    //Wake time prompt
    public void promptAddWakeTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter wake time (HH:mm): ");
        String wakeTimeStr = scanner.nextLine();

        try {
            LocalTime wakeTime = LocalTime.parse(wakeTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
            LocalDate currentDate = LocalDate.now();
            LocalDateTime wakeDateTime = LocalDateTime.of(currentDate, wakeTime);

            addWakeTime(Date.from(wakeDateTime.atZone(ZoneId.systemDefault()).toInstant()));
            System.out.println("Added wake time.");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please try again.");
        }
    }

    public void printSleepObjects() {
        System.out.println("Sleep Records:");
        if (sleepList.isEmpty()) {
            System.out.println("No sleep records found.");
        } else {
            System.out.println();
            for (Sleep sleep : sleepList) {
                System.out.println("Sleep Time: " + sleep.getSleepTime());
                System.out.println("Wake Time: " + sleep.getWakeTime());
                System.out.println();
            }
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Sleep Manager Menu");
            System.out.println("1. Add Sleep Time");
            System.out.println("2. Add Wake Time");
            System.out.println("3. Calculate Average Sleep per Week");
            System.out.println("4. Print Out Sleep Records");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    promptAddSleepTime();
                    break;
                case 2:
                    promptAddWakeTime();
                    break;
                case 3:
                    double averageSleepPerWeek = calculateAverageSleepPerNumDays(1);
                    System.out.println("Average Sleep per Week: " + averageSleepPerWeek + " hours");
                    break;
                case 4:
                    printSleepObjects();
                    break;
                case 5:
                    System.out.println("Exiting Sleep Manager. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println(); // Print an empty line for separation
        }
    }
}
