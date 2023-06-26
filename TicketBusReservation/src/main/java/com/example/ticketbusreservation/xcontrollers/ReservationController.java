package com.example.ticketbusreservation.xcontrollers;

import com.example.ticketbusreservation.dtoRequests.ReservationRequestDto;
import com.example.ticketbusreservation.records.ReservationResponseDto;
import com.example.ticketbusreservation.service.interfaces.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/reservation")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    @PostMapping("/reserve")
    private void reserve(@RequestBody ReservationRequestDto reservationRequestDto,
                         @RequestHeader("Authorization") String authorizationHeader){
        this.reservationService.reserveBusTicket(reservationRequestDto, authorizationHeader);
    }
    @PostMapping("/scan")
    private void scan(@RequestBody ReservationRequestDto reservationRequestDto,
                      @RequestHeader("Authorization") String authorizationHeader){
        this.reservationService.scanCode(reservationRequestDto, authorizationHeader);
    }
    @GetMapping("/my-reservations")
    private List<ReservationResponseDto> getAllUnusedReservations(@RequestHeader("Authorization") String authorizationHeader){
        return this.reservationService.getReservationByUser(authorizationHeader);
    }
}
