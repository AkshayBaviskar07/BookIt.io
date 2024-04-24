package com.bookit.Ticket.controller;

import com.bookit.Ticket.model.Ticket;
import com.bookit.Ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/book")
    public ResponseEntity<String> bookTickets(@RequestParam Integer numberOfTickets,
                                               @RequestParam Long passengerId,
                                               @RequestParam Long trainId) {

        boolean isTicketBooked = ticketService.bookTickets(numberOfTickets, passengerId, trainId);
        if(isTicketBooked) {
            return new ResponseEntity<>("Ticket booked successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();

        if(!tickets.isEmpty()) {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/train-id")
    public ResponseEntity<List<Ticket>> getAllTicketsByTrainId(@RequestParam Long trainId) {
        List<Ticket> tickets = ticketService.getAllTicketsForTrain(trainId);
        if(!tickets.isEmpty()) {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/passenger-id")
    public ResponseEntity<List<Ticket>> getAllTicketsByPassengerId(@RequestParam Long passengerId) {
        List<Ticket> tickets = ticketService.getAllTicketsByPassengerId(passengerId);
        if(!tickets.isEmpty()) {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cancel/{ticketId}")
    public ResponseEntity<String> deleteTicketsByPassengerId(@PathVariable Long ticketId,
                                                              @RequestParam Long passengerId,
                                                              @RequestParam Long trainId,
                                                              @RequestParam Integer canceledTickets) {
        boolean isTicketCanceled = ticketService.deleteTicketsByPassengerId(ticketId, passengerId, trainId, canceledTickets);
        if(isTicketCanceled) {
            return new ResponseEntity<>("Tickets canceled successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
