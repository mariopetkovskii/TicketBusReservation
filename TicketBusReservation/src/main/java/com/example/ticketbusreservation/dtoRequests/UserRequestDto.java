package com.example.ticketbusreservation.dtoRequests;

import lombok.Getter;

@Getter
public class UserRequestDto {
    String firstName;
    String lastName;
    String email;
    String password;
    String confirmPassword;
}
