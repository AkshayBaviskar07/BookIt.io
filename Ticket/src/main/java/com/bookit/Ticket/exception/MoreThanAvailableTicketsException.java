package com.bookit.Ticket.exception;


public class MoreThanAvailableTicketsException extends RuntimeException{
    public MoreThanAvailableTicketsException(String message) {
        super(message);
    }
}
