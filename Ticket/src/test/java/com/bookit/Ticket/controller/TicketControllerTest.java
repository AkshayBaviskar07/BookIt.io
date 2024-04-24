package com.bookit.Ticket.controller;

import com.bookit.Ticket.external.Passenger;
import com.bookit.Ticket.external.Train;
import com.bookit.Ticket.model.Ticket;
import com.bookit.Ticket.service.TicketService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketController.class)
class TicketControllerTest {
    @MockBean
    private TicketService ticketService;
    @Autowired
    private TicketController underTest;
    @Autowired
    private MockMvc mockMvc;
    Ticket ticket = null;
    Passenger passenger = null;
    Train train = null;

    @BeforeEach
    void setUp() {
        ticket = new Ticket(1L, 100.00, true, 1, LocalDate.now(), 1L, 1L);
         }

    @AfterEach
    void tearDown() {
        ticket = null;

    }

    @Test
    void test_bookTickets() throws Exception{
        when(ticketService.bookTickets(1, 1L, 1L)).thenReturn(true);
        this.mockMvc.perform(get("/tickets/book?numberOfTickets=1&passengerId=1&trainId=1"))
                .andExpect(status().isCreated()).andDo(print());
    }

    @Test
    void getAllTickets() throws Exception{
        when(ticketService.getAllTickets()).thenReturn(List.of(ticket));
        this.mockMvc.perform(get("/tickets"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void test_getAllTicketsByTrainId() throws Exception{
        when(ticketService.getAllTicketsForTrain(ticket.getTrainId())).thenReturn(List.of(ticket));
        this.mockMvc.perform(get("/tickets")
                .param("trainId", ticket.getTrainId().toString()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getAllTicketsByPassengerId() throws Exception {
        when(ticketService.getAllTicketsByPassengerId(ticket.getPassengerId())).thenReturn(List.of(ticket));
        this.mockMvc.perform(get("/tickets")
                .param("passengerId", ticket.getPassengerId().toString()))
                .andExpect(status().isOk())
                .andDo(print());
    }
}