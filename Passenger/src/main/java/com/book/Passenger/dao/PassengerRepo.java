package com.book.Passenger.dao;

import com.book.Passenger.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepo extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByName(String name);
}
