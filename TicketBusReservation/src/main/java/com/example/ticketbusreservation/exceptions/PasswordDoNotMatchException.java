package com.example.ticketbusreservation.exceptions;

public class PasswordDoNotMatchException extends RuntimeException{
    public PasswordDoNotMatchException() {
        super("Password do not match!");
    }
}