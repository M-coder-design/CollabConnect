package com.example.CollabConnect.service;

import com.example.CollabConnect.entity.postgres.PostgresUser;
import com.example.CollabConnect.repository.postgres.PostgresUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PostgresUserService {
    @Autowired
    private PostgresUserRepository postgresUserRepository;

    @Cacheable(value = "users", key = "#id")
    public PostgresUser getUserById(Long id) {
        System.out.println("Fetching from PostgreSQL for ID: " + id);
        return postgresUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @CacheEvict(value = "users", key = "#id")
    public void updateUser(Long id, String newName) {
        PostgresUser user = postgresUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        user.setName(newName);
        postgresUserRepository.save(user);
        System.out.println("Updated user with ID: " + id);
    }

}
