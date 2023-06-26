package com.example.ticketbusreservation.exceptions;

public class AccessForbiddenException extends RuntimeException{
    public AccessForbiddenException() {
        super("Access forbidden");
    }
}