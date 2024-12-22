package com.example.CollabConnect.service;

import com.example.CollabConnect.entity.mysql.MySQLUser;
import com.example.CollabConnect.entity.postgres.PostgresUser;
import com.example.CollabConnect.repository.mysql.MySQLUserRepository;
import com.example.CollabConnect.repository.postgres.PostgresUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PostgresUserRepository postgresUserRepository;
    @Autowired
    private MySQLUserRepository mysqlUserRepository;

    public void saveUsers() {
        // Save to PostgreSQL
        PostgresUser postgresUser = new PostgresUser();
        postgresUser.setId(1L);
        postgresUser.setName("Postgres User");
        postgresUserRepository.save(postgresUser);

        // Save to MySQL
        MySQLUser mysqlUser = new MySQLUser();
        mysqlUser.setId(1L);
        mysqlUser.setEmail("mysqluser@example.com");
        mysqlUserRepository.save(mysqlUser);
    }
}
