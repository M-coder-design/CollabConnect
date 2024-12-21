package com.example.CollabConnect;

import com.example.CollabConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CollabConnectApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(CollabConnectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		userService.createUser();
//		userService.deleteUser();
		try{
			userService.deleteUserException();
		}catch (Exception e)
		{

		}

	}
}
