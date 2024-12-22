package com.example.CollabConnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class ChatPublisher {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ChannelTopic channelTopic;

    public void publish(String message){
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
        System.out.println("Published message: " + message);
    }
}
