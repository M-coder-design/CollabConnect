package com.example.CollabConnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void saveToCache(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        System.out.println("Saved to Redis: " + key + " -> " + value);
    }

    public String getFromCache(String key) {
        String value = (String) redisTemplate.opsForValue().get(key);
        System.out.println("Retrieved from Redis: " + key + " -> " + value);
        return value;
    }
}
