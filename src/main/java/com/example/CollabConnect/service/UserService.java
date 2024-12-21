package com.example.CollabConnect.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void createUser() throws InterruptedException {
        Thread.sleep(500); // simulating a delay
        System.out.println("Creating a new user...");
    }

    public void deleteUser() throws InterruptedException {
        Thread.sleep(300); // Simulating a delay
        System.out.println("Deleting a user...");
    }

    public void deleteUserException() {
        System.out.println("Deleting a user...");
        throw new RuntimeException("User not found!");
    }
}
