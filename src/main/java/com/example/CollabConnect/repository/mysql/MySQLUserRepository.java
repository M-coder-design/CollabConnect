package com.example.CollabConnect.repository.mysql;

import com.example.CollabConnect.entity.mysql.MySQLUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySQLUserRepository extends JpaRepository<MySQLUser, Long> {
}
