package com.example.CollabConnect.service;

import com.example.CollabConnect.model.ChatMessage;
import org.springframework.stereotype.Service;

@Service
public class ChatSubscriber {

    public void handleMessage(ChatMessage message) {
        System.out.println("Received message from " + message.getSender() +
                " at " + message.getLocalDateTime() + ": " + message.getContent());
    }
}
