package com.example.ticketbusreservation.records;

public record RelationResponseDto(Long id, String from, String to, Double price, String estimatedTravelTime) {
}
