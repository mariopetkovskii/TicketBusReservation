package com.example.ticketbusreservation.repository;

import com.example.ticketbusreservation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
