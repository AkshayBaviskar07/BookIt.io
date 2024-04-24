package com.bookit.Ticket.repository;

import com.bookit.Ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {
    List<Ticket> findByTrainId(Long trainId);
    List<Ticket> findByPassengerId(Long passengerId);
}
