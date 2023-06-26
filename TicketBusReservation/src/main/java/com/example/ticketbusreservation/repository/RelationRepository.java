package com.example.ticketbusreservation.repository;

import com.example.ticketbusreservation.models.Relation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationRepository extends JpaRepository<Relation, Long> {
}
