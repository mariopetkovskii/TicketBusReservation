package com.example.ticketbusreservation.exceptions;

public class UserNotEnabledException extends RuntimeException{
    public UserNotEnabledException() {
        super("User is not enabled");
    }
}
