package com.example.CollabConnect.service;

import org.springframework.stereotype.Service;

@Service
public class ChatSubscriber {

    public void handleMessage(String message)
    {
        System.out.println("Received message: " + message);
    }
}
