package com.book.Passenger.service;

import com.book.Passenger.dao.PassengerRepo;
import com.book.Passenger.exception.PassengerNotFoundException;

import com.book.Passenger.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepo passengerRepo;

    public Passenger registerPassenger(Passenger passenger) {
       if(passenger != null){
           return passengerRepo.save(passenger);
       } else {
           throw new PassengerNotFoundException("Details cannot be empty!");
       }
    }

    public List<Passenger> getAllPassengers() {
        // fetch all available passengers details
        List<Passenger>passengers = passengerRepo.findAll();

        if(!passengers.isEmpty()){
            return passengers;
        } else{
            // data not available
            throw new PassengerNotFoundException("Records not found!");
        }
    }

    public Passenger getPassengerById(Long id) {
        Optional<Passenger> passengerOptional = passengerRepo.findById(id);

        if(passengerOptional.isPresent()){
            return passengerOptional.get();
        } else{
            throw new PassengerNotFoundException("Not found");
        }
    }


    public boolean updatePassenger(Passenger updatedPassenger, Long passengerId) {
        Optional<Passenger> passengerOptional = passengerRepo.findById(passengerId);

        if(passengerOptional.isPresent()){
            Passenger passenger = passengerOptional.get();
            passenger.setName(updatedPassenger.getName());
            passenger.setPhone(updatedPassenger.getPhone());
            passenger.setCity(updatedPassenger.getCity());
            passenger.setEmail(updatedPassenger.getEmail());
            passenger.setTicketId(updatedPassenger.getTicketId());

            passengerRepo.save(passenger);
            return true;
        } else{
            throw new PassengerNotFoundException("Not found.");
        }
    }

    public boolean deletePassengerById(Long passengerId) {
        Optional<Passenger> optional = passengerRepo.findById(passengerId);
        if (optional.isPresent()){
            passengerRepo.deleteById(passengerId);
            return true;
        } else{
            throw new PassengerNotFoundException("Not Found");
        }
    }

}
