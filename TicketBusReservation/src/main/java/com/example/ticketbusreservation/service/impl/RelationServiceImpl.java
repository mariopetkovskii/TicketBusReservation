package com.example.ticketbusreservation.service.impl;

import com.example.ticketbusreservation.mappers.RelationResponseDtoMapper;
import com.example.ticketbusreservation.records.RelationResponseDto;
import com.example.ticketbusreservation.repository.RelationRepository;
import com.example.ticketbusreservation.service.interfaces.RelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelationServiceImpl implements RelationService {
    private final RelationRepository relationRepository;
    private final RelationResponseDtoMapper relationResponseDtoMapper;
    @Override
    public List<RelationResponseDto> listAll() {
        return this.relationRepository.findAll()
                .stream().map(relationResponseDtoMapper)
                .collect(Collectors.toList());
    }
}
