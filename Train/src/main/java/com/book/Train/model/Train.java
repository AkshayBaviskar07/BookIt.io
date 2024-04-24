package com.book.Train.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id")
    private Long trainId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String departure;

    @Column(nullable = false)
    private String arrival;

    @Column(name = "travel_amount", nullable = false)
    private Double travelAmount;

    @Column(name = "available_seats", nullable = false)
    private Long availableSeats;

    @ElementCollection
    private List<Long> tickets;

    public Train(Long trainId, String name, String departure, String arrival, Double travelAmount, Long availableSeats) {
        this.trainId = trainId;
        this.name = name;
        this.departure = departure;
        this.arrival = arrival;
        this.travelAmount = travelAmount;
        this.availableSeats = availableSeats;
    }
}
