package com.bookit.Ticket.external;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Train {

    private Long trainId;
    private String name;
    private String departure;
    private String arrival;
    private Double travelAmount;
    private Integer availableSeats;
    private List<Long> tickets;
}
