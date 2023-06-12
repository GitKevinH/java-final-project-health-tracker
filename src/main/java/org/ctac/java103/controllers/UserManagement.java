package org.ctac.java103.controllers;

import org.ctac.java103.models.User;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;

public class UserManagement implements Serializable {
    private LinkedList<User> userList;


    public UserManagement() {
        userList = new LinkedList<>();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void removeUser(User user) {
        userList.remove(user);
    }

    public LinkedList<User> getUserList() {
        return userList;
    }

    public void addNewUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the username of the user: ");
        String username = scanner.nextLine();

        if (getUserByUsername(username) != null) {
            System.out.println("Username already exists. Cannot create a new user with the same username.");
            return;
        }

        User user = new User(username);
        addUser(user);
        System.out.println("User added successfully.");
    }

    public void findUserByUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the username to find: ");
        String username = scanner.nextLine();
        User user = getUserByUsername(username);
        if (user != null) {
            System.out.println("User found: " + user.getUsername());
            user.menu();
        } else {
            System.out.println("User not found.");
        }
    }

    private User getUserByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }


}

