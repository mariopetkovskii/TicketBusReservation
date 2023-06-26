package com.example.ticketbusreservation.mappers;

import com.example.ticketbusreservation.models.Relation;
import com.example.ticketbusreservation.records.RelationResponseDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RelationResponseDtoMapper implements Function<Relation, RelationResponseDto> {
    @Override
    public RelationResponseDto apply(Relation relation) {
        return new RelationResponseDto(
                relation.getId(),
                relation.getRelationFrom().getCity(),
                relation.getRelationTo().getCity(),
                relation.getPrice(),
                relation.getEstimatedTravelTime()
        );
    }
}
