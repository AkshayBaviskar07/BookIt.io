package com.book.Passenger.controller;

import com.book.Passenger.model.Passenger;
import com.book.Passenger.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping("register")
    public ResponseEntity<Passenger> registerPassenger(@RequestBody Passenger passenger){
        // enroll passenger
        return new ResponseEntity<>(passengerService.registerPassenger(passenger), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getPassengers(){
        List<Passenger> passengers = passengerService.getAllPassengers();

        if(!passengers.isEmpty())
            return new ResponseEntity<>(passengers, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{passengerId}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long passengerId){
        Passenger passenger = passengerService.getPassengerById(passengerId);

        if(passenger != null)
            return new ResponseEntity<>(passenger, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{passengerId}")
    public  ResponseEntity<String> updatePassenger(@RequestBody Passenger updatedPassenger,
                                                   @PathVariable Long passengerId){
        boolean isUpdated = passengerService.updatePassenger(updatedPassenger, passengerId);
        if(isUpdated)
            return new ResponseEntity<>("Record updated", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{passengerId}")
    public ResponseEntity<String> deletePassenger(@PathVariable Long passengerId){
        boolean isDeleted = passengerService.deletePassengerById(passengerId);
        if(isDeleted)
            return new ResponseEntity<>("Record deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
