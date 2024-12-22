package com.example.CollabConnect.repository.postgres;

import com.example.CollabConnect.entity.postgres.PostgresUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresUserRepository extends JpaRepository<PostgresUser, Long> {
}
