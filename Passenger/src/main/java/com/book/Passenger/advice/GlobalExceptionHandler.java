package com.book.Passenger.advice;

import com.book.Passenger.exception.PassengerNotFoundException;
import com.book.Passenger.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception exception) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(exception.getMessage());
        errorDetails.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PassengerNotFoundException.class)
    public ResponseEntity<ErrorDetails> passengerNotFoundException(PassengerNotFoundException exception) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(exception.getMessage());
        errorDetails.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
