package com.example.CollabConnect;

import com.example.CollabConnect.entity.postgres.PostgresUser;
import com.example.CollabConnect.service.ChatPublisher;
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

//	@Autowired
//	private RedisService redisService;
//
//	@Autowired
//	private PostgresUserService postgresUserService;

	@Autowired
	private ChatPublisher chatPublisher;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CollabConnectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		chatPublisher.publish("Hello, this is a test message!");
		chatPublisher.publish("Real-time chat with Redis is working!");
	}
}
