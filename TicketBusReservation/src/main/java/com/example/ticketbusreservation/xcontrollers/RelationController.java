package com.example.ticketbusreservation.xcontrollers;

import com.example.ticketbusreservation.records.RelationResponseDto;
import com.example.ticketbusreservation.service.interfaces.RelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/relation")
public class RelationController {
    private final RelationService relationService;

    @GetMapping("/list-all")
    private List<RelationResponseDto> listAll(){
        return this.relationService.listAll();
    }
}
