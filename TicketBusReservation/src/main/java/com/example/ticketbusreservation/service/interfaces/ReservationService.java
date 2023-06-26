package com.example.ticketbusreservation.service.interfaces;

import com.example.ticketbusreservation.dtoRequests.ReservationRequestDto;
import com.example.ticketbusreservation.records.ReservationResponseDto;

import java.util.List;

public interface ReservationService {
    void reserveBusTicket(ReservationRequestDto reservationRequestDto, String token);

    void scanCode(ReservationRequestDto reservationRequestDto, String token);

    List<ReservationResponseDto> getReservationByUser(String token);
}
