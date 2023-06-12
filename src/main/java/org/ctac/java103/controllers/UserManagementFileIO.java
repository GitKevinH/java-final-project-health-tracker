package org.ctac.java103.controllers;

import java.nio.file.*;
import java.io.*;

public class UserManagementFileIO {
    public static void saveUserManagement(UserManagement userManagement, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            outputStream.writeObject(userManagement);
            System.out.println("User info saved.");
        } catch (IOException e) {
            System.out.println("Error saving UserManagement: " + e.getMessage());
        }
    }

    public static UserManagement loadUserManagement(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
            UserManagement userManagement = (UserManagement) inputStream.readObject();
            System.out.println("User info loaded.");
            return userManagement;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading UserManagement: " + e.getMessage());
        }
        return null;
    }
}
