package com.bookit.Ticket.exception;

public class TicketNotFound extends RuntimeException{
    public TicketNotFound(String message) {
        super(message);
    }
}
