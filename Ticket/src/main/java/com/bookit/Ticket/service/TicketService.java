package com.bookit.Ticket.service;

import com.bookit.Ticket.client.PassengerClient;
import com.bookit.Ticket.client.TrainClient;
import com.bookit.Ticket.exception.MoreThanAvailableTicketsException;
import com.bookit.Ticket.exception.TicketNotFound;
import com.bookit.Ticket.external.Passenger;
import com.bookit.Ticket.external.Train;
import com.bookit.Ticket.model.Ticket;
import com.bookit.Ticket.repository.TicketRepo;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TicketService {
    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private PassengerClient passengerClient;
    @Autowired
    private TrainClient trainClient;

    public boolean bookTickets(Integer numberOfTickets, Long passengerId, Long trainId) {
        List<Long> bookedTicketIds = new ArrayList<>();

        Ticket ticket = new Ticket();

        Passenger passenger = passengerClient.getPassengerById(passengerId);
        if(passenger != null) {
            Train train = trainClient.getTrainById(trainId);
            if(train != null) {
                if(train.getAvailableSeats() >= numberOfTickets) {

                    ticket.setTotalTickets(numberOfTickets);
                    double totalTravelAmount = train.getTravelAmount() * numberOfTickets;
                    ticket.setTotalAmount(totalTravelAmount);
                    ticket.setPassengerId(passengerId);
                    ticket.setTrainId(trainId);
                    ticket.setBookingDate(LocalDate.now());
                    ticket.setPassengerId(passengerId);
                    ticket.setTrainId(trainId);
                    ticket.setStatus(true);

                    passenger.setTicketId(ticket.getTicketId());

                    bookedTicketIds.add(ticket.getTicketId());
                    train.setTickets(bookedTicketIds);
                    train.setAvailableSeats(train.getAvailableSeats() - numberOfTickets);

                    ticketRepo.save(ticket);
                    String passMsg = passengerClient.updatePassenger(passenger, ticket.getPassengerId());
                    System.out.println(passMsg);
                    String trainMsg = trainClient.updateTrainInfo(ticket.getTrainId(), train);
                    System.out.println(trainMsg);
                    return true;

                } else {
                    throw new MoreThanAvailableTicketsException(train.getAvailableSeats() + "seats available.");
                }
            }
        }
        return false;
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = ticketRepo.findAll();
        if (!tickets.isEmpty()) {
            return tickets;
        } else {
            throw new TicketNotFound("Tickets not found");
        }
    }

    public List<Ticket> getAllTicketsForTrain(Long trainId) {
        List<Ticket> tickets = ticketRepo.findByTrainId(trainId);
        if (!tickets.isEmpty()) {
            return tickets;
        } else {
            throw new TicketNotFound("Tickets not found");
        }
    }

    public List<Ticket> getAllTicketsByPassengerId(Long passengerId) {
        List<Ticket> tickets = ticketRepo.findByPassengerId(passengerId);
        if (!tickets.isEmpty()) {
            return tickets;
        } else {
            throw new TicketNotFound("Tickets not found");
        }
    }

    public boolean deleteTicketsByPassengerId(Long ticketId, Long passengerId, Long trainId, Integer canceledTickets) {
        Passenger passenger = passengerClient.getPassengerById(passengerId);
        if(passenger != null) {
            Train train = trainClient.getTrainById(trainId);
            if(train != null) {

                Optional<Ticket> ticketOptional = ticketRepo.findById(ticketId);
                if(ticketOptional.isPresent()) {

                    // Remove passenger id from ticket
                    ticketOptional.get().setPassengerId(null);
                    ticketOptional.get().setTrainId(null);
                    train.getTickets().remove(canceledTickets);

                    ticketRepo.deleteById(ticketId);
                    passengerClient.updatePassenger(passenger, passengerId);
                    trainClient.updateTrainInfo(trainId, train);

                    return true;
                }
            }
        }
        return false;
    }

}
