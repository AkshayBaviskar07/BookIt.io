package com.bookit.Ticket.client;

import com.bookit.Ticket.external.Passenger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PASSENGER-SERVICE")
public interface PassengerClient {

    @PutMapping("/passenger/{passengerId}")
    String updatePassenger(@RequestBody Passenger updatedPassenger, @PathVariable Long passengerId);

    @GetMapping("/passenger/{passengerId}")
    Passenger getPassengerById(@PathVariable Long passengerId);
}
