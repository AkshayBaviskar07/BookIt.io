package com.book.Passenger.exception;

public class PassengerNotFoundException extends RuntimeException{
    public PassengerNotFoundException(String message) {
        super(message);
    }
}
