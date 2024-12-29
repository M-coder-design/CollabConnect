package com.example.CollabConnect.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ChatMessage implements Serializable {
    private String sender;
    private String content;
    private LocalDateTime localDateTime;

    public ChatMessage() {}

    public ChatMessage(String sender, String content, LocalDateTime localDateTime) {
        this.sender = sender;
        this.content = content;
        this.localDateTime = localDateTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
