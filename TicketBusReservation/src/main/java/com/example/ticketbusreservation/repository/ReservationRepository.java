package com.example.ticketbusreservation.repository;

import com.example.ticketbusreservation.models.Reservation;
import com.example.ticketbusreservation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUserAndIsUsed(User user, Boolean isUsed);
}
