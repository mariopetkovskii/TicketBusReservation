package com.example.ticketbusreservation.service.impl;

import com.example.ticketbusreservation.dtoRequests.ReservationRequestDto;
import com.example.ticketbusreservation.jwtdecored.JwtDecoder;
import com.example.ticketbusreservation.mappers.ReservationResponseDtoMapper;
import com.example.ticketbusreservation.models.Relation;
import com.example.ticketbusreservation.models.Reservation;
import com.example.ticketbusreservation.models.User;
import com.example.ticketbusreservation.records.ReservationResponseDto;
import com.example.ticketbusreservation.repository.RelationRepository;
import com.example.ticketbusreservation.repository.ReservationRepository;
import com.example.ticketbusreservation.repository.UserRepository;
import com.example.ticketbusreservation.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RelationRepository relationRepository;
    private final ReservationResponseDtoMapper reservationResponseDtoMapper;
    @Override
    public void reserveBusTicket(ReservationRequestDto reservationRequestDto, String token) {
        Relation relation = this.relationRepository.findById(reservationRequestDto.getRelationId()).orElseThrow();
        User user = this.userRepository.findByEmail(JwtDecoder.decodeJwtToGetEmail(token));
        this.reservationRepository.save(new Reservation(user, relation));
    }

    @Override
    public void scanCode(ReservationRequestDto reservationRequestDto, String token) {
        Reservation reservation = this.reservationRepository.findById(reservationRequestDto.getReservationId()).orElseThrow();
        User user = reservation.getUser();
        User loggedInUser = this.userRepository.findByEmail(JwtDecoder.decodeJwtToGetEmail(token));
        if(loggedInUser.getEmail().equals(user.getEmail())){
            reservation.setIsUsed(true);
            this.reservationRepository.save(reservation);
        }
    }

    @Override
    public List<ReservationResponseDto> getReservationByUser(String token) {
        User loggedInUser = this.userRepository.findByEmail(JwtDecoder.decodeJwtToGetEmail(token));
        return this.reservationRepository.findAllByUserAndIsUsed(loggedInUser, false)
                .stream().map(reservationResponseDtoMapper)
                .collect(Collectors.toList());
    }

}
