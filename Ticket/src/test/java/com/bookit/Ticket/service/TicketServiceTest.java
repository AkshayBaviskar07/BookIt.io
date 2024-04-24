package com.bookit.Ticket.service;

import com.bookit.Ticket.client.PassengerClient;
import com.bookit.Ticket.client.TrainClient;
import com.bookit.Ticket.external.Passenger;
import com.bookit.Ticket.external.Train;
import com.bookit.Ticket.model.Ticket;
import com.bookit.Ticket.repository.TicketRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TicketServiceTest {

    @InjectMocks
    private TicketService underTest;
    @Mock
    private TicketRepo ticketRepo;
    @Mock
    private PassengerClient passengerClient;
    @Mock
    private TrainClient trainClient;
    Passenger passenger = null;
    Train train = null;
    Ticket ticket = null;

    @BeforeEach
    void setUp() {
        List<Long> ticketIds = new ArrayList<>();
        ticketIds.add(1L);
        ticket = new Ticket(1L, 100.00, true, 1, LocalDate.now(), 1L, 1L);
        passenger = new Passenger(1L, "Rohan", "rohan@gmail.com", "987456321", "Nashik", 1L);
        train = new Train(1L, "Demo Train", "Demo Departure ", "Demo Arrival", 1000.00, 10, ticketIds);
    }

    @AfterEach
    void tearDown() {
        ticket = null;
        passenger = null;
        train = null;
    }

    @Test
    void bookTickets() {

        ticket.setTotalAmount(train.getTravelAmount() * ticket.getTotalTickets());

        // Mock passengerClient and train client to get passenger and train by Id
        when(passengerClient.getPassengerById(1L)).thenReturn(passenger);
        when(trainClient.getTrainById(1L)).thenReturn(train);
        // Mock ticketRepo to save ticket
        when(ticketRepo.save(ticket)).thenReturn(ticket);

        // Getting actual response from service class
        boolean actualResponse = underTest.bookTickets(1, 1L, 1L);

        // Asserting response
        assertTrue(actualResponse);
        assertEquals(9, train.getAvailableSeats());
        assertEquals(ticket.getPassengerId(), passenger.getId());

        // Verifying calls
        verify(passengerClient, times(1)).updatePassenger(passenger, ticket.getPassengerId());
        verify(trainClient, times(1)).updateTrainInfo(ticket.getTrainId(), train);
        verify(ticketRepo, times(1)).save(ticket);
    }

    @Test
    void getAllTickets() {
        when(ticketRepo.findAll()).thenReturn(List.of(ticket));

        List<Ticket> actualResponse = underTest.getAllTickets();
        assertEquals(1, actualResponse.size());
    }

    @Test
    void getAllTicketsForTrain() {
        when(ticketRepo.findByTrainId(1L)).thenReturn(List.of(ticket));

        List<Ticket> actualResponse = underTest.getAllTicketsForTrain(1L);
        assertEquals(1, actualResponse.size());
    }

    @Test
    void getAllTicketsByPassengerId() {
        when(ticketRepo.findByPassengerId(1L)).thenReturn(List.of(ticket));

        List<Ticket> actualResponse = underTest.getAllTicketsByPassengerId(1L);
        assertEquals(1, actualResponse.size());
    }

    @Test
    void deleteTicketsByPassengerId() {

        when(ticketRepo.findById(1L)).thenReturn(java.util.Optional.of(ticket));
        when(passengerClient.getPassengerById(1L)).thenReturn(passenger);
        when(trainClient.getTrainById(1L)).thenReturn(train);

        boolean actualResponse = underTest.deleteTicketsByPassengerId(1L, 1L, 1L, 1);
        assertTrue(actualResponse);
        assertNull(ticket.getPassengerId());
        assertNull(ticket.getTrainId());

        verify(ticketRepo, times(1)).findById(1L);
        verify(passengerClient, times(1)).updatePassenger(passenger, 1L);
        verify(trainClient, times(1)).updateTrainInfo(1L, train);
    }
}