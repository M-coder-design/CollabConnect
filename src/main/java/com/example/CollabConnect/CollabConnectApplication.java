package com.example.CollabConnect;

import com.example.CollabConnect.entity.postgres.PostgresUser;
import com.example.CollabConnect.service.PostgresUserService;
import com.example.CollabConnect.service.RedisService;
import com.example.CollabConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CollabConnectApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private RedisService redisService;

	@Autowired
	private PostgresUserService postgresUserService;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CollabConnectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		redisService.saveToCache("username", "john_doe");
//		redisService.getFromCache("username");
		// Simulate database query
		PostgresUser user1 = postgresUserService.getUserById(1L);
		System.out.println("First Query: " + user1.getName());

		// Simulate cache hit
		PostgresUser user2 = postgresUserService.getUserById(1L);
		System.out.println("Second Query: " + user2.getName());
	}
}
