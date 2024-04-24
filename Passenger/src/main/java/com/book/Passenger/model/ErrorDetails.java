package com.book.Passenger.model;

import java.time.LocalDateTime;

public class ErrorDetails {

    private String message;
    private LocalDateTime localDateTime;

    // getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
