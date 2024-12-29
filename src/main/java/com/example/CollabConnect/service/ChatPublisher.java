package com.example.CollabConnect.service;

import com.example.CollabConnect.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatPublisher {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ChannelTopic channelTopic;

    public void publish(String sender, String content){
        ChatMessage chatMessage = new ChatMessage(sender,content, LocalDateTime.now());
        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);
        System.out.println("Published message: " + content);
        System.out.println("Publishing to channel: " + channelTopic.getTopic());
    }
}
