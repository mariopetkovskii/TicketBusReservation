package com.example.ticketbusreservation.exceptions;

public class UserNotExistsException extends RuntimeException{
    public UserNotExistsException() {
        super("User not exists");
    }
}