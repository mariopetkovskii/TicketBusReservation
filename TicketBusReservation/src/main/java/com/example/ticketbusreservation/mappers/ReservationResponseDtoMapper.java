package com.example.ticketbusreservation.mappers;

import com.example.ticketbusreservation.models.Reservation;
import com.example.ticketbusreservation.records.ReservationResponseDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ReservationResponseDtoMapper implements Function<Reservation, ReservationResponseDto> {
    @Override
    public ReservationResponseDto apply(Reservation reservation) {
        return new ReservationResponseDto(reservation.getId(),
                reservation.getRelation().getRelationFrom().getCity(),
                reservation.getRelation().getRelationTo().getCity(),
                reservation.getRelation().getPrice());
    }
}
