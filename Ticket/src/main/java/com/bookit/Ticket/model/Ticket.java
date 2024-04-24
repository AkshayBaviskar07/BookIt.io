package com.bookit.Ticket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_no")
    private Long ticketId;
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;
    @Column(name = "Boolean", nullable = false)
    private Boolean status;
    @Column(name = "total_tickets", nullable = false)
    private Integer totalTickets;
    @Temporal(TemporalType.DATE)
    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;
    @Column(name = "passenger_id", nullable = false)
    private Long passengerId;
    @Column(name = "train_id", nullable = false)
    private Long trainId;

}
