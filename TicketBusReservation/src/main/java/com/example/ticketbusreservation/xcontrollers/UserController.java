package com.example.ticketbusreservation.xcontrollers;

import com.example.ticketbusreservation.dtoRequests.UserRequestDto;
import com.example.ticketbusreservation.models.User;
import com.example.ticketbusreservation.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRequestDto userRequestDto) {
        return this.userService.register(userRequestDto)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
