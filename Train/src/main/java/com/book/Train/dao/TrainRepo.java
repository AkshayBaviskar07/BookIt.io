package com.book.Train.dao;

import com.book.Train.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainRepo extends JpaRepository<Train, Long> {

    Optional<Train> findByDepartureAndArrival(String departure, String arrival);

}
